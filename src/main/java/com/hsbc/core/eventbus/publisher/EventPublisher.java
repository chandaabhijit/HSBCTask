package com.hsbc.core.eventbus.publisher;

/***
 *
 * Interface which should be implemented by classes that want to publish events
 * on the {@link com.hsbc.core.eventbus.EventBus}
 *
 * @param <T>
 */
public interface EventPublisher<T> {

    void publish(T eventObject);

    void unpublish(T eventObject);

}
