package com.tonmoy1912.commands.implementations;

import com.tonmoy1912.events.Event;

public class EmailCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String template;

    public EmailCommand(Event event) {
        this.details.receiver = event.getUser();
        this.details.message = event.getMessage();
        this.link = event.getLink();
    }

    public NotificationDetails getDetails() {
        return details;
    }
}
