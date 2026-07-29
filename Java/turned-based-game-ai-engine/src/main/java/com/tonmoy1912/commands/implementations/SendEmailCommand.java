package com.tonmoy1912.commands.implementations;

import com.tonmoy1912.game.User;

public class SendEmailCommand {
    User receiver;
    String message;
    String link;
    String templateId;
    String template;

    public SendEmailCommand(User receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

}
