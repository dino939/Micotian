//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.denger.naomitian.module.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.denger.naomitian.module.*;
import com.denger.naomitian.module.movement.*;
import com.denger.naomitian.utils.util.Command;

public class DeathCoords extends Module
{
    public static int deley;
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (DeathCoords.mc.player.getHealth() < 0.0f || DeathCoords.mc.player.isDead || !DeathCoords.mc.player.isEntityAlive()) {
            ++DeathCoords.deley;
            if (DeathCoords.deley == 0) {
                Command.sendClientSideMessage(String.valueOf(new StringBuilder().append(" DeathCoords! X:").append((int)DeathCoords.mc.player.posX).append(" Y:").append((int)DeathCoords.mc.player.posY).append(" Z:").append((int)DeathCoords.mc.player.posZ)));
            }
            if (DeathCoords.deley >= 10) {
                DeathCoords.deley = -1;
            }
        }
    }
    
    public DeathCoords() {
        super("DeathCoords",  Category.OUTHER);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
}
