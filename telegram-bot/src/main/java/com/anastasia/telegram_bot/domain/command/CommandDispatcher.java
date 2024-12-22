package com.anastasia.telegram_bot.domain.command;

import com.anastasia.telegram_bot.domain.session.ChatSessionService;
import com.anastasia.telegram_bot.utils.ChatBotUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class CommandDispatcher {

    private final Map<String, BotCommandHandler> commandHandlerStore;
    private final ChatSessionService chatSessionService;

    @Autowired
    public CommandDispatcher(ApplicationContext applicationContext, ChatSessionService chatSessionService) {
        commandHandlerStore = collectHandlers(applicationContext);
        this.chatSessionService = chatSessionService;
    }


    public Mono<BotApiMethodMessage> apply(Message message) {
        return chatSessionService
                .get(message.getChatId())
                .flatMap(chatSession -> {
                    BotCommandHandler handler = commandHandlerStore.get(message.getText());
                    if (handler != null) {
                        chatSession.getContext().setStep(0);
                    } else {
                        BotCommands sessionContextCommand = chatSession.getContext().getCommand();
                        if (sessionContextCommand != null) {
                            handler = commandHandlerStore.get(sessionContextCommand.value);
                        } else {
                            handler = commandHandlerStore.get(null);
                        }
                    }
                    log.info("Command handler {} is applied", handler.getClass().getSimpleName());
                    return handler.handle(message, chatSession);
                });
    }

    public Mono<BotApiMethodMessage> apply(CallbackQuery callbackQuery) {
        // Callback query pattern - ${COMMAND}:${STEP}:${DATA} (for example, MARKET:1:NASDAQ)
        return chatSessionService
                .get(callbackQuery.getFrom().getId())
                .flatMap(chatSession -> {
                    Locale locale = ChatBotUtility.getLocale(callbackQuery);
                    String[] callback = ChatBotUtility.callBackData(callbackQuery);
                    String text = callback[2];
                    BotCommands command = BotCommands.valueOf(callback[0]);
                    int step = Integer.parseInt(callback[1]);
                    if (chatSession.getContext().getStep() != step) {
                        chatSession.getContext().setStep(step);
                    }
                    BotCommandHandler handler = commandHandlerStore.get(command.value);
                    if (handler == null) {
                        handler = commandHandlerStore.get(null);
                    }
                    log.info("Command handler {} is applied", handler.getClass().getSimpleName());
                    return handler.handle(text, chatSession, locale);
                });
    }


    private Map<String, BotCommandHandler> collectHandlers(ApplicationContext applicationContext) {
        Map<String, BotCommandHandler> commandHandlers = new HashMap<>();
        applicationContext
                .getBeansOfType(BotCommandHandler.class)
                .forEach((key, value) -> {
                    CommandHandler handlerAnnotation = value.getClass().getAnnotation(CommandHandler.class);
                    if (handlerAnnotation != null) {
                        String command = handlerAnnotation.command().value;
                        commandHandlers.put(command, value);
                        log.info("Handler for bot command '{}' is registered", command);
                    }
                });
        BotCommandHandler badRequestHandler = applicationContext.getBean("badRequestHandler", BotCommandHandler.class);
        commandHandlers.put(null, badRequestHandler);
        return commandHandlers;
    }
}
