
package com.denger.naomitian.module.hud;

import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.notifications.notifications;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Notifications extends Module
{
    private static RenderItem itemRender;
    private Entity target;
    public Notifications() {
        super("Notifications", Category.HUD);
    }



    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        if(event.getType() != RenderGameOverlayEvent.ElementType.TEXT || Minecraft.getMinecraft().gameSettings.showDebugInfo)return;
        notifications.show();
    }
}
