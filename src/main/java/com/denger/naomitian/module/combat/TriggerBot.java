package com.denger.naomitian.module.combat;

import com.denger.naomitian.Micotian;
import com.denger.naomitian.settings.Setting;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.denger.naomitian.manager.FriendManager;

import java.util.ArrayList;
import java.util.List;

public class TriggerBot extends Module {
    private Entity entity;
    public static List<String> Freand = new ArrayList<String>();
    public TriggerBot() {
        super("TriggerBot", Category.COMBAT);

        Micotian.instance.settingsManager.rSetting(new Setting("OnlyCritical", this, false));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        RayTraceResult objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
        boolean onlyCrits = Micotian.instance.settingsManager.getSettingByName(this, "OnlyCritical").getValBoolean();

        if (objectMouseOver != null) {
            if (objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
                entity = objectMouseOver.entityHit;

                if (entity instanceof EntityPlayer) {
                    if (mc.player.onGround && onlyCrits) {
                        mc.player.motionY = 0.38;
                    }

                    if (Minecraft.getMinecraft().player.getCooledAttackStrength(0) == 1) {
                        Minecraft.getMinecraft().playerController.attackEntity(Minecraft.getMinecraft().player, entity);
                        if (entity != null && !FriendManager.isFriend(entity.getName())) {
                            Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
                            Minecraft.getMinecraft().player.resetCooldown();
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent e) {
        if (this.isToggled()) {

            for (EntityPlayer player : mc.world.playerEntities) {
                if (player != null && player != mc.player) {
                    if (player != null &&  !FriendManager.isFriend(player.getName())) {
                        player.setEntityBoundingBox(new AxisAlignedBB(
                                player.posX - 0.3,
                                player.getEntityBoundingBox().minY,
                                player.posZ - 0.3,
                                player.posX + 0.3,
                                player.getEntityBoundingBox().maxY,
                                player.posZ + 0.3
                        ));
                    }

                    if (player != null &&  FriendManager.isFriend(player.getName())) {
                        player.setEntityBoundingBox(new AxisAlignedBB(
                                player.posX - 0.0,
                                player.getEntityBoundingBox().minY,
                                player.posZ - 0.0,
                                player.posX + 0.0,
                                player.getEntityBoundingBox().maxY,
                                player.posZ + 0.0
                        ));
                    }



            }
        }
    }

}}
