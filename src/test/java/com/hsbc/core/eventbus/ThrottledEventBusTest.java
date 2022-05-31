package com.hsbc.core.eventbus;

import com.hsbc.core.TestData;
import com.hsbc.core.TestEventSubscriber;
import com.hsbc.core.throttler.Throttler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ThrottledEventBusTest {

    private Throttler throttler = mock(Throttler.class);
    private TestEventSubscriber testEventSubscriber = mock(TestEventSubscriber.class);
    private ThrottledEventBus throttledEventBus = new ThrottledEventBus(throttler);

    private TestData testData = new TestData();

    @BeforeEach
    void setUp() {
        throttledEventBus.addSubscriber(testData, testEventSubscriber);
    }

    @Test
    void testCallbackReceievedWhenMessageRateLessThanMax() {
        when(throttler.shouldProceed(any())).thenReturn(Throttler.ThrottleResult.PROCEED);

        throttledEventBus.publishEvent(testData);
        verify(testEventSubscriber, times(1)).callBackOnAdd(testData);
    }

    @Test
    void testNoCallbackReceivedWhenMessageRateHIgherThanMax() {
        when(throttler.shouldProceed(any())).thenReturn(Throttler.ThrottleResult.DO_NOT_PROCEED);

        throttledEventBus.publishEvent(testData);
        verify(testEventSubscriber, times(0)).callBackOnAdd(testData);
    }
}