package com.tonmoy1912.events;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    List<Event> events = new ArrayList<>();
    List<Subscriber> subscribers = new ArrayList<>();

    public void publish(Event event) {
        events.add(event);
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}
