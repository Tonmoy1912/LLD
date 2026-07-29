package com.tonmoy1912.services;

import com.tonmoy1912.commands.implementations.SendEmailCommand;
import com.tonmoy1912.game.User;

public class EmailService {
    private void sendEmail(User receiver, String message) {
        // todo: mail is sent here.
    }

    public void send(SendEmailCommand command) {
        sendEmail(command.getReceiver(), command.getMessage());
    }
}
