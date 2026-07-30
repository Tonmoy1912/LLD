package com.tonmoy1912.commands.implementations;

import com.tonmoy1912.events.Event;

public class SMSCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String template;

    public SMSCommand(Event event) {
        this.details.receiver = event.getUser();
        this.details.message = event.getMessage();
        this.link = event.getLink();
    }

    public SMSCommand(NotificationDetails details) {
        this.details = details;
    }

    public NotificationDetails getDetails() {
        return details;
    }
}
