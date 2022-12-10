package com.denger.naomitian.module.hud;

import com.denger.naomitian.Micotian;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.utils.Font.CastomFontUtils;
import com.denger.naomitian.utils.MathUtils;
import com.denger.naomitian.utils.RenderUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ModuleList extends Module
{

    ArrayList<SubModule> modules;
    
    public ModuleList() {
        super("ArrayList",  Category.RENDER);
        this.modules = new ArrayList<SubModule>();
    }

    @Override
    public void onDisable() {
        super.onDisable();


    }

    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        final int x = 958;
        final int y = 22;
        for (final SubModule module : this.modules) {
            if (module.getModule().isToggled() && module.getModule().visible){
            module.setAnim(MathUtils.lerp(module.getAnim(), 1.0f, 0.1f));
            module.setY((int)MathUtils.lerp((float)module.getY(), (float)(CastomFontUtils.fr4.getHeight() + 10), 0.1f));}
            else { module.setAnim(MathUtils.lerp(module.getAnim(), 0.0f, 0.1f));
                module.setY((int)MathUtils.lerp((float)module.getY(), 0.0f, 0.1f));}
        }
        this.modules.sort(new Comparator<SubModule>() {
            @Override
            public int compare(final SubModule o1, final SubModule o2) {
                return CastomFontUtils.fr4.getStringWidth(o2.getModule().getName()) - CastomFontUtils.fr4.getStringWidth(o1.getModule().getName());
            }
        });
        int i = 0;
        for (final SubModule module2 : this.modules) {
            GL11.glPushMatrix();
            final float oXpos = (float)(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName()));
            final float oYpos = (float)(y + i);
            final float oWidth = (float)CastomFontUtils.fr4.getStringWidth(module2.getModule().getName());
            final float oHeight = 10.0f;
            GL11.glTranslated(oWidth, oHeight, 1.0);
            GL11.glTranslated(-oXpos * module2.getAnim() + oXpos + oWidth * -module2.getAnim(), -oYpos * 1.0f + oYpos + oHeight * -1.0f, 1.0);
            GL11.glScaled(module2.getAnim(), 1.0, 0.0);
            RenderUtils.drawShadowRect(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName()), y + i, x, y + i + 10, 2);
            RenderUtils.drawRect((float)(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName())), (float)(y + i), (float)x, (float)(y + i + 9), new Color(30, 30, 30, 200).getRGB());
            CastomFontUtils.fr4.drawString(module2.getModule().getName(), (float)(x - CastomFontUtils.fr4.getStringWidth(module2.getModule().getName())), (float)(y + i), ClickGUI.getColor());
            GL11.glPopMatrix();
            i += module2.getY();
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        final int x = 958 - 3;
        this.modules.clear();
        for (final Module module : Micotian.moduleManager.modules) {
            this.modules.add(new SubModule(module));
        }
    }
    
    static class SubModule
    {
        Module module;
        int y;
        float anim;
        
        public SubModule(final Module module) {
            this.y = 0;
            this.anim = 0.0f;
            this.module = module;
        }
        
        public float getAnim() {
            return this.anim;
        }
        
        public void setAnim(final float anim) {
            this.anim = anim;
        }
        
        public Module getModule() {
            return this.module;
        }
        
        public int getY() {
            return this.y;
        }
        
        public void setY(final int y) {
            this.y = y;
        }
    }
}
