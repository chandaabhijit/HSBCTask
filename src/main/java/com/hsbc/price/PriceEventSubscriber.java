package com.hsbc.price;

import com.hsbc.core.eventbus.EventBus;
import com.hsbc.price.data.PriceDataEvent;
import com.hsbc.core.eventbus.subscriber.EventSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceEventSubscriber implements EventSubscriber<PriceDataEvent> {
    private EventBus eventBus;
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceEventSubscriber.class.getName());

    public PriceEventSubscriber(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.addSubscriber(new PriceDataEvent("Mkt1", Double.NaN, Double.NaN), this);
    }

    @Override
    public void callBackOnAdd(PriceDataEvent object) {
        LOGGER.info("Received PriceDataEvent event [{}] on Thread [{}]", object, Thread.currentThread().getId());
    }
}
