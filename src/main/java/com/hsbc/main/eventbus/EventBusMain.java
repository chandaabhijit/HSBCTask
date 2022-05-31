package com.hsbc.main.eventbus;

import com.hsbc.core.eventbus.EventBus;
import com.hsbc.core.eventbus.EventBusImpl;
import com.hsbc.price.PriceEventPublisher;
import com.hsbc.price.PriceEventSubscriber;
import com.hsbc.price.data.PriceDataEvent;

public class EventBusMain {

    public static void main(String... args) {
        EventBus eventBus = new EventBusImpl();
        PriceEventPublisher priceEventPublisher = new PriceEventPublisher(eventBus);

        PriceEventSubscriber priceEventSubscriber = new PriceEventSubscriber(eventBus);

        PriceDataEvent priceDataEvent = new PriceDataEvent("Mkt1", 102, 103);
        priceEventPublisher.publish(priceDataEvent);
        Thread.yield();
        eventBus.shutdownBus();
    }
}
