package com.denger.naomitian.module.misc;

import com.denger.naomitian.Micotian;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SelfDestruct extends Module {
    public static boolean self = false;
    public SelfDestruct() {
        super("SelfDestruct", Category.OUTHER);

    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {

    }


    @Override
    public void onEnable() {
        super.onEnable();

        mc.displayGuiScreen(null);
        for(Module mod : Micotian.instance.moduleManager.getModuleList()){
            if(mod.getName() != "SelfDestruct"){
                //mod.setToggled(false);
            }

        }
        self = true;
        mc.player.rotationYaw = 0;
        mc.player.rotationPitch = 0;
    }


}
