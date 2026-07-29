package com.tonmoy1912.commands.builders;

import com.tonmoy1912.commands.implementations.SMSCommand;
import com.tonmoy1912.game.User;

public class SendSMSCommandBuilder {
    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;
    String template;

    public SendSMSCommandBuilder receiver(User receiver) {
        this.notificationBuilder.receiver(receiver);
        return this;
    }

    public SendSMSCommandBuilder message(String message) {
        this.notificationBuilder.message(message);
        return this;
    }

    public SMSCommand build() {
        return new SMSCommand(notificationBuilder.build());
    }

}
