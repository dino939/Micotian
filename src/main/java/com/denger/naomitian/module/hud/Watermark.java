//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.denger.naomitian.module.hud;

import com.denger.naomitian.utils.Font.CastomFontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.denger.naomitian.*;
import com.denger.naomitian.module.*;
import com.denger.naomitian.settings.*;
import com.denger.naomitian.utils.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Watermark extends Module
{
    public static java.util.List<String> Modes = new ArrayList<String>();
    private final FontRenderer fr;

    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        final String valString = Micotian.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {


             if (Objects.equals(valString, "Skeet")) {
                 boolean nameshow = (boolean) Micotian.instance.settingsManager.getSettingByName(this, "Show Name").getValBoolean();
                 String name = nameshow ? mc.player.getName() : "NoName";
                final int[] rainbow3 = getRainbow(5, 0.1f);
                RGBtoHex(rainbow3[0], rainbow3[1], rainbow3[2]);
                final StringBuilder obj = new StringBuilder(String.valueOf(new StringBuilder().append((Object)new StringBuilder(String.valueOf(new StringBuilder().append((Object)new StringBuilder(String.valueOf(new StringBuilder().append((Object)new StringBuilder().append(Reference.Name).append(" ").append(" | ").append(name).append(" ").append(" | ").append(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())).append(" | Fps ").append(Minecraft.getDebugFPS())))).append(" | ").append(Watermark.mc.isSingleplayer() ? "localhost" : Watermark.mc.getCurrentServerData().serverIP.toLowerCase())))).append("  "))));
                final Minecraft mc = Watermark.mc;
                final String value3 = String.valueOf(obj);
                final float n = (float)(Minecraft.getMinecraft().fontRenderer.getStringWidth(value3) + 6);
                final int n2 = 20;
                final int n3 = 2;
                final int n4 = 2;
                RenderUtil.drawRect(n3, n4, n3 + n + 2.0f, n4 + n2, new Color(5, 5, 5, 255).getRGB());
                RenderUtil.drawBorderedRect(n3 + 0.5, n4 + 0.5, n3 + n + 1.5, n4 + n2 - 0.5, 0.5, new Color(40, 40, 40, 255).getRGB(), new Color(60, 60, 60, 255).getRGB(), true);
                RenderUtil.drawBorderedRect(n3 + 2, n4 + 2, n3 + n, n4 + n2 - 2, 0.5, new Color(22, 22, 22, 255).getRGB(), new Color(60, 60, 60, 255).getRGB(), true);
                RenderUtil.drawRect(n3 + 2.5, n4 + 2.5, n3 + n - 0.5, n4 + 4.5, new Color(9, 9, 9, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0, n4 + 3, 4.0f + n / 3.0f, n4 + 4, new Color(81, 149, 219, 255).getRGB(), new Color(180, 49, 218, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0f + n / 3.0f, n4 + 3, 4.0f + n / 3.0f * 2.0f, n4 + 4, new Color(180, 49, 218, 255).getRGB(), new Color(236, 93, 128, 255).getRGB());
                RenderUtil.drawGradientSideways(4.0f + n / 3.0f * 2.0f, n4 + 3, n / 3.0f * 3.0f + 1.0f, n4 + 4, new Color(236, 93, 128, 255).getRGB(), new Color(235, 255, 0, 255).getRGB());
                //Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(value3, (float)(4 + n3), (float)(7 + n4), -1);
                 mc.fontRenderer.drawStringWithShadow(value3,  (float)(4 + n3),(float)(6 + n4), -1);

            }
            else if (Objects.equals(valString, "NaomitianV2")) {
                 boolean nameshow = (boolean) Micotian.instance.settingsManager.getSettingByName(this, "Show Name").getValBoolean();
                 String name = nameshow ? mc.player.getName() : "NoName";
                 String time = (new SimpleDateFormat("HH:mm")).format(Calendar.getInstance().getTime());
                 String text1 = Reference.Name + " Client " + Reference.Version + " | " + name + " | " + time;
                 Gui.drawRect(4, 3, fr.getStringWidth(text1) + 7, 22,ClickGUI.getColor());
                 Gui.drawRect(5, 5, fr.getStringWidth(text1) + 6, 21, new Color(37, 37, 37, 255).getRGB());
                 //Gui.drawRect(6, 6, fr.getStringWidth(text1) + 10, 8, ClickGUI.getColor());
                 Gui.drawRect(5, 9, fr.getStringWidth(text1) + 7, 9, new Color(20, 20, 20, 100).getRGB());
                 CastomFontUtils.fr.drawStringWithShadow(text1, 7 - 1, 13, -1);
            }
             else if (Objects.equals(valString, "NaomitianV1")) {
                 boolean nameshow = (boolean) Micotian.instance.settingsManager.getSettingByName(this, "Show Name").getValBoolean();
                 String name = nameshow ? mc.player.getName() : "NoName";
                 String time = (new SimpleDateFormat("HH:mm")).format(Calendar.getInstance().getTime());
                 String text1 = Reference.Name + " Client " + Reference.Version + " | " + name + " | " + time;
                 Gui.drawRect(5, 5, fr.getStringWidth(text1) + 9, 21, new Color(37, 37, 37, 255).getRGB());
                 Gui.drawRect(6, 6, fr.getStringWidth(text1) + 8, 8, ClickGUI.getColor());
                 Gui.drawRect(5, 9, fr.getStringWidth(text1) + 9, 9, new Color(20, 20, 20, 100).getRGB());
                 mc.fontRenderer.drawStringWithShadow(text1, 9 , 11, -1);
             }
        }
    }
    
    public static int RGBtoHex(final int n, final int n2, final int n3) {
        return n << 16 | n2 << 8 | n3;
    }
    
    public Watermark() {
        super("Watermark", Category.HUD);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        Watermark.Modes.add("Skeet");
        Watermark.Modes.add("NaomitianV1");
        Watermark.Modes.add("NaomitianV2");
        Micotian.instance.settingsManager.rSetting(new Setting("Show Name", this, true));

        Micotian.instance.settingsManager.rSetting(new Setting("Mode", this, "NaomitianV2", (ArrayList)Watermark.Modes));
    }
    
    public static int[] getRainbow(final int n, final float n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        final float n6 = 6.0f * ((System.currentTimeMillis() - Math.round(n2 * 1000.0f)) % (n * 1000)) / (n * 1000);
        final int round = Math.round(255.0f * (n6 - (float)Math.floor(n6)));
        if (n6 < 1.0f) {
            n3 = 255;
            n4 = round;
        }
        else if (n6 < 2.0f) {
            n3 = 255 - round;
            n4 = 255;
        }
        else if (n6 < 3.0f) {
            n4 = 255;
            n5 = round;
        }
        else if (n6 < 4.0f) {
            n4 = 255 - round;
            n5 = 255;
        }
        else if (n6 < 5.0f) {
            n3 = round;
            n5 = 255;
        }
        else if (n6 < 6.0f) {
            n3 = 255;
            n5 = 255 - round;
        }
        return new int[] { n3, n4, n5 };
    }
}
