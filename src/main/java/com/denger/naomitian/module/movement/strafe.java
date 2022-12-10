
package com.denger.naomitian.module.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;


public class strafe extends Module {
    public static void strafe() {
        strafe(getSpeed());
    }

    public static void strafe(final float f) {
        if (!isMoving()) {
            return;
        }
        final double d = getDirection();
        mc.player.motionX = -Math.sin(d) * f;
        mc.player.motionZ = Math.cos(d) * f;
    }

    public strafe() {
        super("Strafe", Category.MOVEMENT);
    }

    public static float getSpeed() {
        return (float)Math.sqrt(mc.player.motionX * mc.player.motionX + mc.player.motionZ * mc.player.motionZ);
    }

    public static double getDirection() {
        float f = mc.player.rotationYaw;
        if (mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        }
        else if (mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        strafe();
    }

    public static boolean isMoving() {
        return mc.player != null && (mc.player.movementInput.moveForward != 0.0f || mc.player.movementInput.moveStrafe != 0.0f);
    }
}
