package com.denger.naomitian.module.hud;


import com.denger.naomitian.Micotian;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.settings.Setting;
import com.denger.naomitian.utils.ColourUtils;
import com.denger.naomitian.utils.Font.CastomFontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class TEST extends Module {
    public static int xx;
    public static int yy;
    protected Minecraft mc = Minecraft.getMinecraft();
    public TEST() {
        super("Castom", Category.HUD);
        Micotian.instance.settingsManager.rSetting(new Setting("PosX1", this, 10, 0, 1000, true));
        Micotian.instance.settingsManager.rSetting(new Setting("PosY1", this, 10, 0, 1000, true));
        Micotian.instance.settingsManager.rSetting(new Setting("PosX2", this, 10, 0, 1000, true));
        Micotian.instance.settingsManager.rSetting(new Setting("PosY2", this, 10, 0, 1000, true));
        Micotian.instance.settingsManager.rSetting(new Setting("PosX", this, 10, 0, 1000, true));
        Micotian.instance.settingsManager.rSetting(new Setting("PosY", this, 10, 0, 1000, true));
        Micotian.instance.settingsManager.rSetting(new Setting("xx", this, 10, 0, 1000, true));
        Micotian.instance.settingsManager.rSetting(new Setting("yy", this, 10, 0, 1000, true));
    }
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        int xx = (int) Micotian.instance.settingsManager.getSettingByName(this, "xx").getValDouble();
        int yy = (int) Micotian.instance.settingsManager.getSettingByName(this, "yy").getValDouble();
        int posX1 = (int) Micotian.instance.settingsManager.getSettingByName(this, "PosX1").getValDouble();
        int posY1 = (int) Micotian.instance.settingsManager.getSettingByName(this, "PosY1").getValDouble();
        int posX2 = (int) Micotian.instance.settingsManager.getSettingByName(this, "PosX2").getValDouble();
        int posY2 = (int) Micotian.instance.settingsManager.getSettingByName(this, "PosY2").getValDouble();
        int posX = (int) Micotian.instance.settingsManager.getSettingByName(this, "PosX").getValDouble();
        int posY = (int) Micotian.instance.settingsManager.getSettingByName(this, "PosY").getValDouble();
        CastomFontUtils.fr2.drawString("TestTextForCord",posX,posY,ColourUtils.genRainbow());
        CastomFontUtils.fr2.drawString("TestTextForCord",5,5,ColourUtils.genRainbow());
        if (mc.player != null && mc.world != null) {
            Gui.drawRect(posX1, posY1,  posX2, posY2, new Color(37, 37, 37, 255).getRGB());
        }
    }
}

