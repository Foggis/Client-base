package client.features.option;

public abstract class Option<T> {

    private final String name;
    private T value;

    public Option(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
    }

    public String getName() { return name; }
    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }

    public abstract OptionType getType();
}
