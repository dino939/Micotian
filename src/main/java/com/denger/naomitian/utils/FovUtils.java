//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.denger.naomitian.utils;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;

public class FovUtils
{
    public static boolean isInAttackFOV(final Entity entity, final int fov) {
        return getDistanceFromMouse(entity) <= fov;
    }
    
    private static int getDistanceFromMouse(final Entity entity) {
        final float[] neededRotations = getNeededRotations(entity);
        if (neededRotations != null) {
            final float neededYaw = Minecraft.getMinecraft().player.rotationYaw - neededRotations[0];
            final float neededPitch = Minecraft.getMinecraft().player.rotationPitch - neededRotations[1];
            final float distanceFromMouse = MathHelper.sqrt(neededYaw * neededYaw + neededPitch * neededPitch * 2.0f);
            return (int)distanceFromMouse;
        }
        return -1;
    }
    
    private static float[] getNeededRotations(final Entity vec) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.posX - eyesPos.x;
        final double diffY = vec.posY - eyesPos.y;
        final double diffZ = vec.posZ - eyesPos.z;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[] { Minecraft.getMinecraft().player.rotationYaw + MathHelper.wrapDegrees(yaw - Minecraft.getMinecraft().player.rotationYaw), Minecraft.getMinecraft().player.rotationPitch + MathHelper.wrapDegrees(pitch - Minecraft.getMinecraft().player.rotationPitch) };
    }
    
    private static Vec3d getEyesPos() {
        return new Vec3d(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ);
    }
    
    public static String getPlayerName(final EntityPlayer player) {
        return (player.getGameProfile() != null) ? player.getGameProfile().getName() : "null";
    }
}
