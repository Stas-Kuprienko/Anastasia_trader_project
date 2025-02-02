package com.project.telegram.domain.command.impl;

import com.project.telegram.domain.command.BotCommandHandler;
import com.project.telegram.domain.command.BotCommands;
import com.project.telegram.domain.command.CommandHandler;
import com.project.telegram.domain.session.ChatSession;
import com.project.telegram.domain.session.ChatSessionService;
import com.project.telegram.utils.ChatBotUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;
import java.util.Locale;

@CommandHandler(command = BotCommands.CLEAR)
public class ClearCommandHandler extends BotCommandHandler {

    private static final String MESSAGE_KEY = "CLEAR";

    private final MessageSource messageSource;
    private final ChatSessionService chatSessionService;


    @Autowired
    public ClearCommandHandler(MessageSource messageSource, ChatSessionService chatSessionService) {
        this.messageSource = messageSource;
        this.chatSessionService = chatSessionService;
    }


    @Override
    public Mono<BotApiMethod<?>> handle(Message message, ChatSession session) {
        Locale locale = ChatBotUtility.getLocale(message);
        String text = messageSource.getMessage(MESSAGE_KEY, null, locale);
        return Mono.just(session)
                .doOnNext(ChatSession::clear)
                .map(b -> {
                    chatSessionService.save(session).subscribe();
                    return createSendMessage(session.getChatId(), text);
                });
    }

    @Override
    public Mono<BotApiMethod<?>> handle(CallbackQuery callbackQuery, ChatSession session, Locale locale) {
        String textToSend = messageSource.getMessage(MESSAGE_KEY, null, locale);
        return Mono.just(session)
                .doOnNext(ChatSession::clear)
                .map(b -> {
                    chatSessionService.save(session).subscribe();
                    return createSendMessage(session.getChatId(), textToSend);
                });
    }
}
