package com.hsbc.core.eventbus.filter;

public interface EventFilter <T>{

    boolean matches(T oldObject, T newObject);
}
