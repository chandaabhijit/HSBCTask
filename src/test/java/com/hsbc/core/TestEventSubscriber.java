package com.hsbc.core;

import com.hsbc.core.eventbus.subscriber.EventSubscriber;

public class TestEventSubscriber implements EventSubscriber<TestData> {
    @Override
    public void callBackOnAdd(TestData object) {

    }
}
