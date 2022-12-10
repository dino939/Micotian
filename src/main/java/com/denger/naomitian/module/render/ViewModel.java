package com.denger.naomitian.module.render;

import com.denger.naomitian.Micotian;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.settings.Setting;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glTranslated;

public class ViewModel extends Module {
    public static List<String> listA = new ArrayList<String>();

    public ViewModel() {
        super("ViewModel", Category.RENDER);
        Micotian.instance.settingsManager.rSetting(new Setting("PosX", this, 0, -2, 2, false));
        Micotian.instance.settingsManager.rSetting(new Setting("PosY", this, 0, -2, 2, false));
        Micotian.instance.settingsManager.rSetting(new Setting("PosZ", this, 0, -2, 2, false));


    }

    @Override
    public void onDisable() {
        super.onDisable();

    }

    @Override
    public void onEnable() {
        super.onEnable();
    }



    @SubscribeEvent
    public void onRender(final RenderSpecificHandEvent event) {
        float PosX = (float) Micotian.instance.settingsManager.getSettingByName(this, "PosX").getValDouble();
        float PosY = (float) Micotian.instance.settingsManager.getSettingByName(this, "PosY").getValDouble();
        float PosZ = (float) Micotian.instance.settingsManager.getSettingByName(this, "PosZ").getValDouble();

        glTranslated(PosX, PosY, PosZ);

    }
}
