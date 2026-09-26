package client.eventbus;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    // Map of event class -> sorted list of cached listener entries
    private final Map<Class<?>, List<ListenerEntry>> registry = new ConcurrentHashMap<>();


    public void subscribe(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventTarget.class)) continue;
            if (method.getParameterCount() != 1) continue;

            Class<?> eventType = method.getParameterTypes()[0];
            if (!Event.class.isAssignableFrom(eventType)) continue;

            method.setAccessible(true);

            byte priority = method.getAnnotation(EventTarget.class).priority();
            ListenerEntry entry = new ListenerEntry(listener, method, priority);

            registry
                .computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                .add(entry);

            // Keep sorted: highest priority first duh
            registry.get(eventType).sort((a, b) -> Byte.compare(b.priority, a.priority));
        }
    }


    public void unsubscribe(Object listener) {
        for (List<ListenerEntry> entries : registry.values()) {
            entries.removeIf(e -> e.source == listener);
        }
    }


    public <T extends Event> T post(T event) {
        List<ListenerEntry> entries = registry.get(event.getClass());
        if (entries == null || entries.isEmpty()) return event;

        for (ListenerEntry entry : entries) {
            try {
                entry.method.invoke(entry.source, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return event;
    }


    private static final class ListenerEntry {
        final Object source;
        final Method method;
        final byte priority;

        ListenerEntry(Object source, Method method, byte priority) {
            this.source = source;
            this.method = method;
            this.priority = priority;
        }
    }
}