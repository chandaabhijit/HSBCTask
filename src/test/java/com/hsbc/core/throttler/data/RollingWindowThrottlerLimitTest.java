package com.hsbc.core.throttler.data;

import com.hsbc.core.throttler.Throttler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RollingWindowThrottlerLimitTest {

    RollingWindowThrottlerLimit rollingWindowThrottlerLimit;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testObjectCreation() {
        int maxRate = 10;
        int timeWindowInMillis = 10;
        rollingWindowThrottlerLimit = new RollingWindowThrottlerLimit(maxRate, timeWindowInMillis);
        assertEquals(rollingWindowThrottlerLimit.getMaxRate(), maxRate);
        assertEquals(rollingWindowThrottlerLimit.getTimeWindowInMillis(), timeWindowInMillis);
    }

    @Test
    void testShouldProceedDeniedOnExpiryOfMaxRate() {
        int maxRate = 1;
        int timeWindowInMillis = 100;
        rollingWindowThrottlerLimit = new RollingWindowThrottlerLimit(maxRate, timeWindowInMillis);
        assertEquals(rollingWindowThrottlerLimit.shouldProceed(), Throttler.ThrottleResult.PROCEED);
        assertEquals(rollingWindowThrottlerLimit.shouldProceed(), Throttler.ThrottleResult.DO_NOT_PROCEED);

    }

    @Test
    void testShouldProceedSuccessWhenInSlidingWindow() throws InterruptedException {
        int maxRate = 10;
        int timeWindowInMillis = 100;
        rollingWindowThrottlerLimit = new RollingWindowThrottlerLimit(maxRate, timeWindowInMillis);
        assertEquals(Throttler.ThrottleResult.PROCEED, rollingWindowThrottlerLimit.shouldProceed());
        sleep(50);
        assertEquals(Throttler.ThrottleResult.PROCEED, rollingWindowThrottlerLimit.shouldProceed());
    }

    @Test
    void testShouldProceedFailWhenMaxRateExhaustedWithinTimeWindow() throws InterruptedException {
        int maxRate = 1;
        int timeWindowInMillis = 100;
        rollingWindowThrottlerLimit = new RollingWindowThrottlerLimit(maxRate, timeWindowInMillis);
        assertEquals(Throttler.ThrottleResult.PROCEED, rollingWindowThrottlerLimit.shouldProceed());
        sleep(10);
        assertEquals(Throttler.ThrottleResult.DO_NOT_PROCEED, rollingWindowThrottlerLimit.shouldProceed());
    }
}