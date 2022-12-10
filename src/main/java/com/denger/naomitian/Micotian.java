package com.denger.naomitian;


import Bobr.BobrGui;
import Castom.CastomGui;
import com.denger.naomitian.events.event.EventManager;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.manager.ModuleManager;
import com.denger.naomitian.notifications.Type;
import com.denger.naomitian.notifications.notifications;
import com.denger.naomitian.settings.SettingsManager;
import com.denger.naomitian.utils.RotationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Micotian
{

    public static ModuleManager moduleManager;
    public static EventManager eventManager;

    public static Micotian instance;
    public SettingsManager settingsManager;


    private RotationManager rotationManager;
    public static Color color = Color.red;
    public static BobrGui bobrGui;
    public static CastomGui CastomGui;

    public RotationManager getRotationManager() {
        if (this.rotationManager == null) {
            this.rotationManager = new RotationManager();
        }
        return this.rotationManager;
    }

    public Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    public void init() throws Exception {
    	MinecraftForge.EVENT_BUS.register(this);
    	settingsManager = new SettingsManager();
    	moduleManager = new ModuleManager();
        this.rotationManager = new RotationManager();
        bobrGui = new BobrGui();
        CastomGui = new CastomGui();
        notifications.add(String.valueOf(TextFormatting.RED), "successful injection!", Type.OK);
        //ModuleManager.registerModules("ru.internali.module.combat")
        //notification.main("successful injection!");
        //notif.add(Reference.Name, "Hello " + Minecraft.getMinecraft().player.getName() + "!");
        ;

    }

    /*public static Color getClientColor(){
        return color;
    }*/

    @SubscribeEvent
    public void key(KeyInputEvent e) {
    	if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
    		return; 
    	try {
             if (Keyboard.isCreated()) {
                 if (Keyboard.getEventKeyState()) {
                     int keyCode = Keyboard.getEventKey();
                     if (keyCode <= 0)
                    	 return;
                     for (Module m : moduleManager.modules) {
                    	 if (m.getKey() == keyCode && keyCode > 0) {
                    		 m.toggle();
                    	 }
                     }
                 }
             }
         } catch (Exception q) { q.printStackTrace(); }
    }
}
