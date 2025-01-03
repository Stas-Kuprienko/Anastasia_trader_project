package com.anastasia.telegram_bot.domain.command.impl;

import com.anastasia.telegram_bot.domain.command.BotCommandHandler;
import com.anastasia.telegram_bot.domain.command.BotCommands;
import com.anastasia.telegram_bot.domain.command.CommandHandler;
import com.anastasia.telegram_bot.domain.session.ChatSession;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;
import java.util.Locale;

@CommandHandler(command = BotCommands.HELP)
public class HelpCommandHandler extends BotCommandHandler {

    @Override
    public Mono<BotApiMethod<?>> handle(Message message, ChatSession session) {
        return null;
    }

    @Override
    public Mono<BotApiMethod<?>> handle(CallbackQuery callbackQuery, ChatSession session, Locale locale) {
        return null;
    }
}
