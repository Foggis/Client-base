package client.features;

import java.util.*;
import java.util.stream.Collectors;

public final class ModuleManager {

    private static final List<Module> modules = new ArrayList<>();

    private ModuleManager() {}

    public static void start(Module... toRegister) {
        for (Module module : toRegister) {
            modules.add(module);
        }
    }

    public static void onKey(int keyCode) {
        for (Module module : modules) {
            if (module.getKey() == keyCode) {
                module.toggle();
            }
        }
    }




//    public static void enableAll() {
//        modules.forEach(Module::enable);
//    }
//    public static void disableAll() {
//        modules.forEach(Module::disable);
//    }

    public static Optional<Module> get(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    public static <T extends Module> Optional<T> get(Class<T> type) {
        return modules.stream()
            .filter(type::isInstance)
            .map(type::cast)
            .findFirst();
    }

    public static List<Module> getAll() {
        return Collections.unmodifiableList(modules);
    }

    public static List<Module> getByCategory(Category category) {
        return modules.stream()
            .filter(m -> m.getCategory() == category)
            .collect(Collectors.toList());
    }

    public static List<Module> getEnabled() {
        return modules.stream()
            .filter(Module::isEnabled)
            .collect(Collectors.toList());
    }
}
