package client.eventbus;

import client.Client;

public abstract class Event {

    private boolean cancelled;

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
    public void cancel() { this.cancelled = true; }

    @SuppressWarnings("unchecked")
    public <T extends Event> T fire() {
        return (T) Client.EVENT_BUS.post(this);
    }
}
