package com.hsbc.price;

import com.hsbc.core.eventbus.EventBus;
import com.hsbc.price.data.PriceDataEvent;
import com.hsbc.core.eventbus.publisher.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class PriceEventPublisher implements EventPublisher<PriceDataEvent> {
    private EventBus eventBus;
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceEventPublisher.class.getName());
    public PriceEventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    @Override
    public void publish(PriceDataEvent eventObject) {
        eventBus.publishEvent(eventObject);
        LOGGER.info("Published event [{}] on Thread [{}]", eventObject, Thread.currentThread().getId());
    }

    @Override
    public void unpublish(PriceDataEvent eventObject) {
        throw new RuntimeException("Not yet supported");
    }
}
