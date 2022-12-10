package com.denger.naomitian.module.render;

import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FullBright extends Module {
    public float oldgamma;
    public FullBright() {
        super("FullBright", Category.RENDER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        oldgamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 10000f;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.gammaSetting = oldgamma;
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent e) {

    }
}
