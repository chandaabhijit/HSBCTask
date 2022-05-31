package com.hsbc.core.eventbus.subscriber;

import com.hsbc.core.eventbus.EventBus;

/***
 * Interface which should be implemented by classes that want to receive callbacks
 * on the events it is interested in
 *
 * @param <T>
 */
public interface EventSubscriber<T> {

    /***
     * callback to indicate an object has been added to the {@link EventBus}
     * @param object the object that has been added to the EventBus
     */
    void callBackOnAdd(T object);
}
