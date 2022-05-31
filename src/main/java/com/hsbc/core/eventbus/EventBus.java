package com.hsbc.core.eventbus;

import com.hsbc.core.eventbus.filter.EventFilter;
import com.hsbc.core.eventbus.subscriber.EventSubscriber;

/***
 * Objects of type T to be published on the EventBus should implement equals and hashCode methods
 */
public interface EventBus {
    // Feel free to replace Object with something more specific,
    // but be prepared to justify it
    void publishEvent(EventObject o);

    // How would you denote the subscriber?
    void addSubscriber(EventObject object, EventSubscriber eventSubscriber);

    // Would you allow clients to filter the events they receive? How would the interface look like?
    void addSubscriberForFilteredEvents(EventFilter eventFilter);

    boolean isCoalescing();

    void shutdownBus();
}
