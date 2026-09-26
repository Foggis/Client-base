package client.features.impl.module.client;


import client.features.Category;
import client.features.Module;
import client.annotation.ModuleInfo;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

@ModuleInfo(
    name = "ClickGui",
    category = Category.CLIENT,
    description = "Opens this menu",
    defaultKey = GLFW.GLFW_KEY_RIGHT_SHIFT
)
public final class ClickGuiModule extends Module {

    @Override
    protected void onEnable() {
        //Minecraft.getInstance().setScreen(new ClickGui());
        disable();
    }
}