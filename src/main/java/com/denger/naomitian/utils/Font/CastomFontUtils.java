package com.denger.naomitian.utils.Font;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public final class CastomFontUtils
{
    public static CustomFontRenderer fr;
    public static CustomFontRenderer fr2;
    public static CustomFontRenderer fr3;
    public static CustomFontRenderer fr4;
    
    public static Font getFontFromTTF(final ResourceLocation loc, final float fontSize, final int fontType) {
        try {
            Font output = Font.createFont(fontType, Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream());
            output = output.deriveFont(fontSize);
            return output;
        }
        catch (Exception var5) {
            return null;
        }
    }
    
    static {


        CastomFontUtils.fr = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("font.otf"), 20.0f, 0), true, true);
        CastomFontUtils.fr2 = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("main.ttf"),20.0f, 0), true, true);
        CastomFontUtils.fr3 = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("rerty.ttf"),20.0f, 0), true, true);
        CastomFontUtils.fr4 = new CustomFontRenderer(getFontFromTTF(new ResourceLocation("latoregular.ttf"), 20.0f, 0), true, true);
    }
}
