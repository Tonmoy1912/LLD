package com.tonmoy1912.events;

import java.util.function.Consumer;

public class Subscriber {
    private final Consumer<Event> function;

    public Subscriber(Consumer<Event> function) {
        this.function = function;
    }
}
