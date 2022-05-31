package com.hsbc.core.eventbus;

import com.hsbc.core.eventbus.filter.EventFilter;
import com.hsbc.core.eventbus.subscriber.EventSubscriber;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/****
 * Implementation of {@link EventBus} in asynchronous mode by default [publisher thread is not the same as the subscriber thread]
 *
 * Coalescing is disabled by default
 *
 * This implementation uses a ThreadPool to dispatch the messages to the subscriber, the limitation of this approach is the message
 * is not delivered by the same thread to a EventSubscriber. In an improved implementation we can look to reuse a ResourcePool and use
 * dedicated thread for a given EventSubscriber, that will avoid issues with thread safety in an enterprise implementation when you have
 * multiple EventBus subscribers
 */
public class EventBusImpl implements EventBus {
    //store a list of all listeners for
    private final Map<EventObject, List<EventSubscriber>> listenerMap = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(5);
    private final List<EventObject> queue = new LinkedList<>();

    private final boolean coalescing;

    public EventBusImpl() {
        this.coalescing = false;
    }

    public EventBusImpl(boolean coalescing) {
        this.coalescing = coalescing;
    }

    @Override
    public void publishEvent(EventObject o) {
        synchronized (this) {
            queue.add(o);
        }
        executor.submit(() -> drainQueue());
    }

    void drainQueue() {
        for (EventObject obj : queue) {
            List<EventSubscriber> eventSubscriberList = listenerMap.get(obj);
            for (EventSubscriber subscriber : eventSubscriberList) {
                subscriber.callBackOnAdd(obj);
            }
            queue.remove(obj);
        }
    }

    @Override
    public void addSubscriber(EventObject object, EventSubscriber eventSubscriber) {
        List<EventSubscriber> eventSubscriberList = listenerMap.computeIfAbsent(object, k -> new ArrayList());
        eventSubscriberList.add(eventSubscriber);
    }

    @Override
    public void addSubscriberForFilteredEvents(EventFilter eventFilter) {

    }

    @Override
    public boolean isCoalescing() {
        return coalescing;
    }

    @Override
    public void shutdownBus() {
        executor.shutdown();
    }
}
