package com.anastasia.telegram_bot.domain.element;

import com.anastasia.telegram_bot.utils.ChatBotUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class KeyboardMarkups {

    private final MessageSource messageSource;

    @Autowired
    public KeyboardMarkups(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @SafeVarargs
    public final InlineKeyboardMarkup inlineKeyboardMarkup(List<InlineKeyboardButton>... buttons) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttonRows = List.of(buttons);
        keyboardMarkup.setKeyboard(buttonRows);
        return keyboardMarkup;
    }

    public InlineKeyboardMarkup flatInlineKeyboardMarkup(Locale locale, List<String> buttons) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtons = inlineKeyboardButtons(locale, buttons);
        List<List<InlineKeyboardButton>> buttonRows = List.of(keyboardButtons);
        keyboardMarkup.setKeyboard(buttonRows);
        return keyboardMarkup;
    }

    public InlineKeyboardMarkup multiInlineKeyboardMarkup(Locale locale, List<List<String>> buttonLists) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttonRows = buttonLists.stream()
                .map(list -> inlineKeyboardButtons(locale, list))
                .toList();
        keyboardMarkup.setKeyboard(buttonRows);
        return keyboardMarkup;
    }

    public List<InlineKeyboardButton> inlineKeyboardButtons(Locale locale, List<String> values) {
        return values.stream()
                .map(v -> {
                    String label = messageSource
                            .getMessage(ChatBotUtility.extractCallbackKey(v), null, locale);
                    var button = new InlineKeyboardButton(label);
                    button.setCallbackData(v);
                    return button;
                })
                .toList();
    }

    public List<InlineKeyboardButton> inlineKeyboardButtons(Locale locale, String... values) {
        return Arrays.stream(values)
                .map(v -> {
                    String label = messageSource
                            .getMessage(ChatBotUtility.extractCallbackKey(v), null, locale);
                    var button = new InlineKeyboardButton(label);
                    button.setCallbackData(v);
                    return button;
                })
                .toList();
    }

    /**
     * Expects as argument Map of callback data (key) and button label (value).
     * @param values {@link Map} of callback query data and button label.
     * @return List of {@link InlineKeyboardButton}.
     */
    public List<InlineKeyboardButton> inlineKeyboardButtons(Map<String, String> values) {
        return values.entrySet()
                .stream()
                .map(e -> {
                    String callback = ChatBotUtility.extractCallbackKey(e.getKey());
                    var button = new InlineKeyboardButton(e.getValue());
                    button.setCallbackData(callback);
                    return button;
                })
                .toList();
    }
}
