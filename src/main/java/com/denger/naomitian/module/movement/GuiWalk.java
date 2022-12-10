package com.denger.naomitian.module.movement;

import Bobr.BobrGui;
import com.denger.naomitian.Micotian;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.settings.Setting;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class GuiWalk extends Module {

    public GuiWalk() {
        super("GuiWalk", Category.MOVEMENT);
        Micotian.instance.settingsManager.rSetting(new Setting("Sneak", this, false));

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
    public void onKeyUpdate(InputUpdateEvent event) {
        boolean Sneak = (boolean) Micotian.instance.settingsManager.getSettingByName(this, "Sneak").getValBoolean();
        if (mc.world != null && mc.player != null) {
            if (mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiIngameMenu || mc.currentScreen instanceof GuiOptions || mc.currentScreen instanceof BobrGui) {
                if (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())) {
                    ++mc.player.movementInput.moveForward;
                    mc.player.movementInput.forwardKeyDown = true;
                }

                if (Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())) {
                    --mc.player.movementInput.moveForward;
                    mc.player.movementInput.backKeyDown = true;
                }

                if (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
                    --mc.player.movementInput.moveStrafe;
                    mc.player.movementInput.rightKeyDown = true;
                }

                if (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
                    ++mc.player.movementInput.moveStrafe;
                    mc.player.movementInput.rightKeyDown = true;
                }

                mc.player.movementInput.jump = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
                mc.player.movementInput.sneak = Sneak && Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode());

                if (mc.player.movementInput.sneak) {
                    mc.player.movementInput.moveStrafe = (float)((double)mc.player.movementInput.moveStrafe * 0.3D);
                    mc.player.movementInput.moveForward = (float)((double)mc.player.movementInput.moveForward * 0.3D);
                }
            }
        }
    }
}
