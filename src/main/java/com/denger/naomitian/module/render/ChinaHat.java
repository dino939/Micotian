//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.denger.naomitian.module.render;

import com.denger.naomitian.module.hud.ClickGUI;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import com.denger.naomitian.module.*;
import com.denger.naomitian.settings.*;
import com.denger.naomitian.utils.*;
import com.denger.naomitian.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChinaHat extends Module
{    public static java.util.List<String> Modes = new ArrayList<String>();
    
    public ChinaHat() {
        super("ChinaHat", Category.RENDER);
        ChinaHat.Modes.add("V2");
        ChinaHat.Modes.add("V1");
        Micotian.instance.settingsManager.rSetting(new Setting("Mode", this, "V2", (ArrayList<String>) Modes));

    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {
        final String valString = Micotian.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (Objects.equals(valString, "V2")) {
            final double n = ChinaHat.mc.player.isSneaking() ? -0.1 : 0.12;
            if (ChinaHat.mc.gameSettings.thirdPersonView == 1 || ChinaHat.mc.gameSettings.thirdPersonView == 2) {
                GlStateManager.pushMatrix();
                GL11.glBlendFunc(770, 771);
                GlStateManager.disableDepth();
                GlStateManager.disableTexture2D();
                RenderUtils.enableSmoothLine(2.5f);
                GL11.glShadeModel(7425);
                GL11.glDisable(2884);
                GL11.glEnable(3042);
                GL11.glEnable(2929);
                GL11.glTranslatef(0.0f, (float)(ChinaHat.mc.player.height + n), 0.0f);
                GL11.glRotatef(-ChinaHat.mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
                final Color white = Color.WHITE;
                final Color color = new Color(105, 55, 255);
                GL11.glBegin(6);
                RenderUtils.glColor(color, 255.0f);
                GL11.glVertex3d(0.0, 0.3, 0.0);
                for (float n2 = 0.0f; n2 < 360.5; ++n2) {
                    RenderUtils.glColor(ClickGUI.getColor());
                    GL11.glVertex3d(Math.cos(n2 * 3.141592653589793 / 180.0) * 0.66, 0.0, Math.sin(n2 * 3.141592653589793 / 180.0) * 0.66);
                }
                GL11.glVertex3d(0.0, 0.3, 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                for (float n3 = 0.0f; n3 < 360.5; ++n3) {
                    RenderUtils.glColor(ClickGUI.getColor());
                    GL11.glVertex3d(Math.cos(n3 * 3.141592653589793 / 180.0) * 0.66, 0.0, Math.sin(n3 * 3.141592653589793 / 180.0) * 0.66);
                }
                GL11.glEnd();
                GlStateManager.enableAlpha();
                RenderUtils.disableSmoothLine();
                GL11.glShadeModel(7424);
                GL11.glEnable(2884);
                GL11.glDisable(3042);
                GlStateManager.enableTexture2D();
                GlStateManager.enableDepth();
                GlStateManager.resetColor();
                GlStateManager.popMatrix();
            }
        }
        else if (Objects.equals(valString, "V1")) {
            final double n4 = ChinaHat.mc.player.isSneaking() ? -0.18 : 0.04;
            GL11.glPushMatrix();
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDepthMask(false);
            GL11.glColor4f(Color.magenta.getRed() / 255.0f, Color.magenta.getGreen() / 255.0f, Color.magenta.getBlue() / 255.0f, 0.49019608f);
            GL11.glTranslatef(0.0f, (float)((float)(ChinaHat.mc.player.height + 0.36) + n4), 0.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            final Cylinder cylinder = new Cylinder();
            cylinder.setDrawStyle(100000);
            cylinder.setDrawStyle(100011);
            cylinder.draw(0.0f, 0.55f, 0.3f, 150, 100);
            GL11.glEnable(3553);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
    }
}
