package com.hsbc.core.eventbus;

import com.hsbc.core.TestData;
import com.hsbc.core.TestEventSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.mockito.Mockito.*;

class EventBusImplTest {

    private EventBusImpl eventBus = new EventBusImpl();
    private TestData testData = new TestData();
    private TestEventSubscriber testEventSubscriber = mock(TestEventSubscriber.class);

    @BeforeEach
    void setUp() {
        eventBus.addSubscriber(testData, testEventSubscriber);
    }

    @Test
    void publishEvent() throws InterruptedException {
        eventBus.publishEvent(testData);
        sleep(10);
        verify(testEventSubscriber, times(1)).callBackOnAdd(testData);
        eventBus.publishEvent(testData);
        sleep(100);
        verify(testEventSubscriber, times(2)).callBackOnAdd(testData);
    }
}