package com.hsbc.main.throttler;

import com.hsbc.core.eventbus.EventBus;
import com.hsbc.core.eventbus.ThrottledEventBus;
import com.hsbc.price.data.PriceDataEvent;
import com.hsbc.core.throttler.RollingWindowThrottler;
import com.hsbc.core.throttler.Throttler;
import com.hsbc.core.throttler.data.RollingWindowThrottlerLimit;
import com.hsbc.price.PriceEventPublisher;
import com.hsbc.price.PriceEventSubscriber;

import static java.lang.Thread.sleep;

public class RollingWindowThrottlerMain {

    public static void main(String... args) throws InterruptedException {
        Throttler rollingWindowThrottler = new RollingWindowThrottler();
        rollingWindowThrottler.setThrottleLimitForEvent(new PriceDataEvent("Mkt1", Double.NaN, Double.NaN),
                new RollingWindowThrottlerLimit(1, 1000));
        EventBus eventBus = new ThrottledEventBus(rollingWindowThrottler);
        PriceEventPublisher priceEventPublisher = new PriceEventPublisher(eventBus);

        PriceEventSubscriber priceEventSubscriber = new PriceEventSubscriber(eventBus);

        for (int i = 0; i < 100; i++) {

            PriceDataEvent priceDataEvent = new PriceDataEvent("Mkt1", 102 - i/100.0, 103 + i/100.0);
            priceEventPublisher.publish(priceDataEvent);
        }
        sleep(1000);
        PriceDataEvent priceDataEvent = new PriceDataEvent("Mkt1", 999, 999);
        priceEventPublisher.publish(priceDataEvent);


        Thread.yield();
        eventBus.shutdownBus();
    }
}
