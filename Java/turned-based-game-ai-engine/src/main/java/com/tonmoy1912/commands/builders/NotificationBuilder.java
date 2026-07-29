package com.tonmoy1912.commands.builders;

import com.tonmoy1912.commands.implementations.NotificationDetails;
import com.tonmoy1912.game.User;

public class NotificationBuilder {
    User receiver;
    String message;

    public NotificationBuilder receiver(User receiver) {
        this.receiver = receiver;
        return this;
    }

    public NotificationBuilder message(String message) {
        this.message = message;
        return this;
    }

    public NotificationDetails build() {
        return new NotificationDetails(receiver, message);
    }
}
