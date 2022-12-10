package Bobr.buttons.setting;

import com.denger.naomitian.module.Module;
import com.denger.naomitian.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.io.IOException;

public class CSSetting {

	public int x, y, width, height;

	public Setting set;
	public Module mod;

	public String displayString;

	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRenderer;

	public CSSetting(int x, int y, int width, int height, Setting s) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.set = s;
		this.displayString = s.getName();
	}

	public CSSetting(int x, int y, int width, int height, Module s) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.mod = s;
		this.displayString = s.getName();
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	}

	public void keyTyped(char typedChar, int keyCode) throws IOException {
	}

	public void onKeyPress(int typedChar, int keyCode) {
	}

	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {
	}

	public void initButton() {
	}

}
