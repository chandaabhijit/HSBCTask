package com.hsbc.core.throttler;

import com.hsbc.core.eventbus.EventObject;
import com.hsbc.core.throttler.data.RollingWindowThrottlerLimit;

public interface Throttler {

    void setThrottleLimitForEvent(EventObject event, RollingWindowThrottlerLimit rollingWindowThrottlerLimit);
    // check if we can proceed (poll)
    ThrottleResult shouldProceed(EventObject eventObject);

    // subscribe to be told when we can proceed (Push)
    void notifyWhenCanProceed(EventObject eventObject, ThrottlerWindowCallbackHandler callbackHandler);

    enum ThrottleResult {
        PROCEED, // publish, aggregate etc
        DO_NOT_PROCEED //
    }
}
