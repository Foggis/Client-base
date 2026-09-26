package client.features;

import client.Client;
import client.annotation.ModuleInfo;
import client.features.option.Option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Module {

    private final String name;
    private final Category category;
    private final String description;

    private boolean enabled;
    private int key;

    private final List<Option<?>> options = new ArrayList<>();

    protected Module() {
        ModuleInfo info = getClass().getAnnotation(ModuleInfo.class);
        if (info == null) throw new IllegalStateException(getClass().getSimpleName() + " is missing @ModuleInfo");
        this.name = info.name();
        this.category = info.category();
        this.description = info.description();
        this.key  = info.defaultKey();
    }

    public final void enable() {
        if (enabled) return;
        enabled = true;
        Client.EVENT_BUS.subscribe(this);
        onEnable();
    }

    public final void disable() {
        if (!enabled) return;
        enabled = false;
        Client.EVENT_BUS.unsubscribe(this);
        onDisable();
    }

    public final void toggle() {
        if (enabled) disable();
        else enable();
    }

    protected void onEnable()  {}
    protected void onDisable() {}

    protected final <T extends Option<?>> T register(T option) {
        options.add(option);
        return option;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public String getDescription() { return description; }
    public boolean isEnabled() { return enabled; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }

    public List<Option<?>> getOptions() { return Collections.unmodifiableList(options); }
}
