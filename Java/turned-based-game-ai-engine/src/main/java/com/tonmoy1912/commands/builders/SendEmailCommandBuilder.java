package com.tonmoy1912.commands.builders;

import com.tonmoy1912.game.User;
import com.tonmoy1912.commands.implementations.EmailCommand;

public class SendEmailCommandBuilder {
    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;
    String template;

    public SendEmailCommandBuilder link(String link) {
        this.link = link;
        return this;
    }

    public SendEmailCommandBuilder receiver(User receiver) {
        this.notificationBuilder.receiver(receiver);
        return this;
    }

    public SendEmailCommandBuilder message(String message) {
        this.notificationBuilder.message(message);
        return this;
    }

    public EmailCommand build() {
        return new EmailCommand(notificationBuilder.build(), link);
    }
}
