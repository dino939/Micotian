package com.denger.naomitian.module.render;

import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.module.hud.ClickGUI;
import com.denger.naomitian.utils.Font.CastomFontUtils;
import com.denger.naomitian.utils.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class NameTags extends Module
{
    public NameTags() {
        super("NameTags", Category.RENDER);
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
    public void onRender3D(RenderWorldLastEvent event) {
        for (final Entity e : this.mc.world.loadedEntityList) {
            if (e instanceof EntityPlayer && e != this.mc.player) {
                final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * this.mc.getRenderPartialTicks() - this.mc.getRenderManager().viewerPosX;
                final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * this.mc.getRenderPartialTicks() - this.mc.getRenderManager().viewerPosY;
                final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * this.mc.getRenderPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                GL11.glPushMatrix();
                GL11.glDisable(2929);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                final float size = Math.min(Math.max(1.2f * (this.mc.player.getDistance(e) * 0.15f), 1.25f), 6.0f) * 0.015f;
                GL11.glTranslatef((float)x, (float)y + e.height + 0.6f, (float)z);
                GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
                GL11.glScalef(-size, -size, -size);
                final int health = (int)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth() * 100.0f);
                final String name = e.getDisplayName().getUnformattedText() +  " HP: " + health;
                final float wight = (float)(CastomFontUtils.fr4.getStringWidth(name) + 10);
                RenderUtils.drawShadowRect(-(wight / 2.0f), 0.0, wight / 2.0f, 15.0, 3);
                RenderUtils.drawRect(-(wight / 2.0f), 0.0f, wight / 2.0f, 15.0f, new Color(30, 30, 30).getRGB());
                final int b = (int)(wight - 4.0f);
                RenderUtils.drawShadowRect(-(wight / 2.0f) + 2.0f, 11.0, -(wight / 2.0f) + 2.0f + b / ((EntityPlayer)e).getMaxHealth() * ((EntityPlayer)e).getHealth(), 13.0, 1);
                RenderUtils.drawRect(-(wight / 2.0f) + 2.0f, 11.0f, -(wight / 2.0f) + 2.0f + b / ((EntityPlayer)e).getMaxHealth() * ((EntityPlayer)e).getHealth(), 13.0f, ClickGUI.getColor());
                CastomFontUtils.fr4.drawCenteredString(name, 0.0f, (float)(5 - CastomFontUtils.fr4.getHeight() / 2), -1);
                final ArrayList<ItemStack> items = new ArrayList<ItemStack>();
                if (!(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                    items.add(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND));
                }
                for (final ItemStack itemStack : e.getArmorInventoryList()) {
                    if (!(itemStack.getItem() instanceof ItemAir)) {
                        items.add(itemStack);
                    }
                }
                if (!(((EntityPlayer)e).getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                    items.add(((EntityPlayer)e).getHeldItem(EnumHand.OFF_HAND));
                }
                int i = -(items.size() * 16 / 2);
                for (final ItemStack itemStack2 : items) {
                    final RenderItem renderItem = this.mc.getRenderItem();
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    RenderHelper.enableStandardItemLighting();
                    renderItem.zLevel = -100.0f;
                    renderItem.renderItemIntoGUI(itemStack2, i, -20);
                    renderItem.zLevel = 0.0f;
                    RenderHelper.disableStandardItemLighting();
                    GlStateManager.enableAlpha();
                    GlStateManager.disableBlend();
                    GlStateManager.disableLighting();
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                    GlStateManager.popMatrix();
                    i += 16;
                }
                GL11.glEnable(2929);
                GL11.glColor3f(255.0f, 255.0f, 255.0f);
                GL11.glEnable(2929);
                GL11.glPopMatrix();
            }
        }
    }
    
    public int getcenter(final String text) {
        return CastomFontUtils.fr4.getStringWidth(text) / 2;
    }
    
    public int getcenter(final int text) {
        return CastomFontUtils.fr4.getStringWidth(String.valueOf(text)) / 2;
    }
}
