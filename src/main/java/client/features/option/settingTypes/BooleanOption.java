package client.features.option.settingTypes;

import client.features.option.Option;
import client.features.option.OptionType;

public final class BooleanOption extends Option<Boolean> {

    public BooleanOption(String name, boolean defaultValue) {
        super(name, defaultValue);
    }

    public boolean isEnabled() { return getValue(); }

    public void toggle() { setValue(!getValue()); }

    @Override
    public OptionType getType() { return OptionType.BOOLEAN; }
}
