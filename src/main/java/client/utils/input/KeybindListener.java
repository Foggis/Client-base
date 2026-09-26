package client.utils.input;

import client.features.ModuleManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public final class KeybindListener {

    private static boolean started = false;

    private KeybindListener() {}

    public static void start() { started = true; }

    public static void register() {
        long h = Minecraft.getInstance().getWindow().handle();

        var ok = GLFW.glfwSetKeyCallback(h, null);
        GLFW.glfwSetKeyCallback(h, (w, key, sc, action, mods) -> {




            if (ok != null) ok.invoke(w, key, sc, action, mods);
            if (action == GLFW.GLFW_PRESS && Minecraft.getInstance().screen == null) // this makes so modules dont toggle if you're in a minecraft screen




                ModuleManager.onKey(key);
        });


    }
}