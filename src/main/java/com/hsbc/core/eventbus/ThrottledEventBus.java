package com.hsbc.core.eventbus;

import com.hsbc.core.throttler.Throttler;
import com.hsbc.core.throttler.ThrottlerWindowCallbackHandler;

public class ThrottledEventBus extends EventBusImpl implements ThrottlerWindowCallbackHandler {
    private Throttler throttler;

    public ThrottledEventBus(Throttler throttler) {
        super(false);
        this.throttler = throttler;
    }

    @Override
    public void publishEvent(EventObject eventObject) {
        Throttler.ThrottleResult throttleResult = throttler.shouldProceed(eventObject);
        if (Throttler.ThrottleResult.PROCEED.equals(throttleResult)) {
            super.publishEvent(eventObject);
        }
    }

    @Override
    public void onNotifyThrottleWindowOpen() {

    }
}
