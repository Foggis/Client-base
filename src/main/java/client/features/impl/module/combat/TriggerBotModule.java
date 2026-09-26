package client.features.impl.module.combat;

import client.eventbus.events.TickEvent;
import client.eventbus.EventTarget;
import client.features.Category;
import client.features.Module;
import client.annotation.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

@ModuleInfo(name = "TriggerBot",
        category = Category.COMBAT,
        description = "Attacks entities if you have your crosshair on them",
        defaultKey = -1)




public final class TriggerBotModule extends Module {

    private static final Minecraft mc = Minecraft.getInstance();

    @EventTarget
    public void onTick(TickEvent event) {
        if (mc.player == null || mc.hitResult == null || mc.hitResult.getType() != HitResult.Type.ENTITY) return;

        Entity target = ((EntityHitResult) mc.hitResult).getEntity();
        if (target == mc.player || mc.player.getAttackStrengthScale(0f) < 0.9f) return;

        mc.gameMode.attack(mc.player, target);
        mc.player.swing(InteractionHand.MAIN_HAND); // I havent taken a good look at this, but might be worth checking this out: https://www.youtube.com/watch?v=duM2wzsjOwE
    }
}