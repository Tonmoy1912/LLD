package com.tonmoy1912.commands.builders;

import com.tonmoy1912.commands.implementations.SendEmailCommand;
import com.tonmoy1912.game.User;

public class SendEmailCommandBuilder {
    User receiver;
    String message;
    String link;
    String templateId;
    String template;

    public SendEmailCommandBuilder receiver(User receiver) {
        this.receiver = receiver;
        return this;
    }

    public SendEmailCommandBuilder message(String message) {
        this.message = message;
        return this;
    }

    public SendEmailCommand build() {
        return new SendEmailCommand(receiver, message);
    }
}
