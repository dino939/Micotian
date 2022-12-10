//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.denger.naomitian.module.combat;

import com.denger.naomitian.module.*;
import com.denger.naomitian.*;
import com.denger.naomitian.settings.*;
import io.netty.util.internal.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoClicker extends Module
{
    private double holdLength;
    private double min;
    private long hold;
    private double max;
    private double speed;
    private long lastClick;
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.updateVals();
    }
    
    public AutoClicker() {
        super("AutoClicker", Category.COMBAT);
        Micotian.instance.settingsManager.rSetting(new Setting("MinCPS", this, 8.0, 1.0, 20.0, false));
        Micotian.instance.settingsManager.rSetting(new Setting("MaxCPS", this, 12.0, 1.0, 20.0, false));
    }
    
    private void updateVals() {
        this.min = Micotian.instance.settingsManager.getSettingByName(this, "MinCPS").getValDouble();
        this.max = Micotian.instance.settingsManager.getSettingByName(this, "MaxCPS").getValDouble();
        if (this.min >= this.max) {
            this.max = this.min + 1.0;
        }
        this.speed = 1.0 / ThreadLocalRandom.current().nextDouble(this.min - 0.2, this.max);
        this.holdLength = this.speed / ThreadLocalRandom.current().nextDouble(this.min, this.max);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent renderTickEvent) {
        if (Mouse.isButtonDown(0)) {
            if (System.currentTimeMillis() - this.lastClick > this.speed * 1000.0) {
                this.lastClick = System.currentTimeMillis();
                if (this.hold < this.lastClick) {
                    this.hold = this.lastClick;
                }
                final int getKeyCode = AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode();
                KeyBinding.setKeyBindState(getKeyCode, true);
                KeyBinding.onTick(getKeyCode);
                this.updateVals();
            }
            else if (System.currentTimeMillis() - this.hold > this.holdLength * 1000.0) {
                KeyBinding.setKeyBindState(AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                this.updateVals();
            }
        }
    }
}
