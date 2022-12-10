//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 */
package Caesium;

import Caesium.components.Frame;
import com.denger.naomitian.manager.FriendManager;
import com.denger.naomitian.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import com.denger.naomitian.module.hud.ClickGUI;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClickGui
extends GuiScreen {
    public static int compID = 0;
    private ArrayList<Frame> frames = new ArrayList();
    private final FontRenderer fr;

    public ClickGui() {
        this.fr = Minecraft.getMinecraft().fontRenderer;
        compID = 0;
    }

    protected void addFrame(Frame frame) {
        if (!this.frames.contains(frame)) {
            this.frames.add(frame);
        }
    }

    protected ArrayList<Frame> getFrames() {
        return this.frames;
    }

    public void initGui() {
        for (Frame frame : this.frames) {
            frame.initialize();
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (Frame frame : this.frames) {
            frame.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (Frame frame : this.frames) {
            frame.keyTyped(keyCode, typedChar);
        }
    }
    public void drawinfo(int mouseX, int mouseY){
        int xpos = 600;
        int ypos = 280;
        int g = 0;


        fontRenderer.drawStringWithShadow("Friends", xpos + (fontRenderer.getStringWidth("Friends") / 2), ypos - fontRenderer.FONT_HEIGHT, ClickGUI.getColor());

        RenderUtil.drawRect(xpos, ypos,  xpos + 100,  ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT, new Color(50, 50, 50, 190).getRGB());
        RenderUtil.drawNewRect(xpos - 1, ypos, xpos, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT, ClickGUI.getColor());
        RenderUtil.drawNewRect(xpos + 100 - 1, ypos, xpos + 100, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT, ClickGUI.getColor());
        RenderUtil.drawGlow(xpos, ypos - 1, xpos + 100, ypos, ClickGUI.getColor(), 250);
        RenderUtil.drawGlow(xpos, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT - 1, xpos + 100, ypos + FriendManager.FRIENDS.size() * fontRenderer.FONT_HEIGHT, ClickGUI.getColor(), 250);


        for(String friends : FriendManager.FRIENDS){
            int butX1 = 0 + xpos;
            int butX2 =  100 + xpos;
            int butY1 = g + ypos;
            int butY2 = g + fontRenderer.FONT_HEIGHT + ypos;

            if(mouseX > butX1 && mouseX < butX2 && mouseY > butY1 && mouseY < butY2){
                fontRenderer.drawString(friends,   xpos + 50 - fontRenderer.getStringWidth(friends) / 2, g + ypos, ClickGUI.getColor());
                if(Mouse.isButtonDown(1)){
                    FriendManager.toggleFriend(friends);
                    return;
                }
            } else{
                fontRenderer.drawString(friends,  xpos + 50 - fontRenderer.getStringWidth(friends) / 2, g + ypos, Color.white.getRGB());
            }
            g += fontRenderer.FONT_HEIGHT;
        }





    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sR = new ScaledResolution(this.mc);
        //this.fr.drawString("Denger#6075", 2, sR.getScaledHeight() - this.fr.FONT_HEIGHT, Panel.fontColor);

        Gui.drawRect(64, 556,  0, 524, new Color(37, 37, 37, 255).getRGB());
        Gui.drawRect(0, 525,  64, 527, ClickGUI.getColor());

        mc.fontRenderer.drawString("ByDenger",4,529,Panel.fontColor);
        for (Frame frame : this.frames) {
            frame.render(mouseX, mouseY);
            drawinfo(mouseX, mouseY);
        }
    }
}

