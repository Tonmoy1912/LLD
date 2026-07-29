package com.tonmoy1912.services;

import com.tonmoy1912.commands.implementations.SMSCommand;
import com.tonmoy1912.game.User;

public class SMSService {
    private void sendEmail(User receiver, String message) {
        // todo: mail is sent here.
    }

    public void send(SMSCommand command) {
        sendEmail(command.getDetails().getReceiver(), command.getDetails().getMessage());
    }
}
