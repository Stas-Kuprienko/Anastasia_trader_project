package com.project.notifications.domain.notifiers;

import com.project.notifications.domain.message.TemplateStore;
import com.project.notifications.service.EmailService;
import com.project.notifications.service.TelegramService;
import com.project.events.TradeSubscriptionEvent;
import org.springframework.beans.factory.annotation.Autowired;

@Notifier(eventType = TradeSubscriptionEvent.class)
public class TradeSubscriptionNotifier implements EventNotifier<TradeSubscriptionEvent> {

    private final TemplateStore templateStore;
    private final TelegramService telegramService;
    private final EmailService emailService;


    @Autowired
    public TradeSubscriptionNotifier(TemplateStore templateStore,
                                     TelegramService telegramService,
                                     EmailService emailService) {
        this.templateStore = templateStore;
        this.telegramService = telegramService;
        this.emailService = emailService;
    }


    @Override
    public void apply(TradeSubscriptionEvent tradeSubscriptionEvent) {
        //TODO just for test
        String key = tradeSubscriptionEvent.getClass().getSimpleName();
        key += '_' + "EN";
        String template = templateStore.getTemplate(key);
        emailService.sendHtmlEmail(template.formatted(tradeSubscriptionEvent.toString()), "?");
    }


}
