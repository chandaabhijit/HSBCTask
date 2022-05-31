package com.hsbc.core.throttler;

import com.hsbc.core.eventbus.EventObject;
import com.hsbc.core.throttler.data.RollingWindowThrottlerLimit;

import java.util.HashMap;
import java.util.Map;

public class RollingWindowThrottler implements Throttler {

    private Map<EventObject, RollingWindowThrottlerLimit> perEventThrottlerConfig = new HashMap<>();
    private Map<EventObject, ThrottlerWindowCallbackHandler> perEventThrottlerCallbackHandler = new HashMap<>();

    public RollingWindowThrottler() {
    }

    @Override
    public void setThrottleLimitForEvent(EventObject event, RollingWindowThrottlerLimit rollingWindowThrottlerLimit) {
        perEventThrottlerConfig.put(event, rollingWindowThrottlerLimit);
    }

    @Override
    public ThrottleResult shouldProceed(EventObject eventObject) {
        RollingWindowThrottlerLimit throttlerLimit = perEventThrottlerConfig.get(eventObject);
        return throttlerLimit.shouldProceed();
    }

    @Override
    public void notifyWhenCanProceed(EventObject eventObject, ThrottlerWindowCallbackHandler callbackHandler) {
        perEventThrottlerCallbackHandler.put(eventObject, callbackHandler);
    }
}
