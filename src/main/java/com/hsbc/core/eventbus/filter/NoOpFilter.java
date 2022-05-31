package com.hsbc.core.eventbus.filter;

/***
 * No op filter that passes everything
 * @param <T> the type to filter
 */

public class NoOpFilter<T> implements EventFilter<T>{
    /***
     *
     * @param oldObject
     * @param newObject
     * @return always true
     */
    @Override
    public boolean matches(T oldObject, T newObject) {
        return true;
    }
}
