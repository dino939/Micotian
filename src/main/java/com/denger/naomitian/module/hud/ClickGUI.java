package com.denger.naomitian.module.hud;


import Caesium.Panel;
import com.denger.naomitian.Micotian;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.settings.Setting;
import com.denger.naomitian.utils.ColourUtils;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ClickGUI extends Module {
	public static int red;
	public static int green;
	public static int blue;
	public static int color;

	public static List<String> Modes = new ArrayList<String>();
	public ClickGUI() {
		super("ClickGUI", Category.HUD);
		this.setKey(Keyboard.KEY_RSHIFT);
		Modes.add("Castom");
		Modes.add("New");
		Modes.add("Old");
		Micotian.instance.settingsManager.rSetting(new Setting("Gui", this, "Castom", (ArrayList<String>) Modes));
		Micotian.instance.settingsManager.rSetting(new Setting("Rainbow", this, true));
		Micotian.instance.settingsManager.rSetting(new Setting("Red", this, 225, 0, 225, true));
		Micotian.instance.settingsManager.rSetting(new Setting("Green", this, 0, 0, 225, true));
		Micotian.instance.settingsManager.rSetting(new Setting("Blue", this, 0, 0, 225, true));
	}



	public static int getColor() {
		int color;
		boolean setcolor = (boolean) Micotian.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();

		float[] hue = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
		int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
		int red = (int) Micotian.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
		int green =  (int) Micotian.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
		int blue = (int) Micotian.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
		color = ColourUtils.toRGBA(red, green, blue, 255);
		int TheColor = setcolor ? ColourUtils.genRainbow() : color;

		return TheColor;
	}
	public static int getColor2() {
		int color;
		boolean setcolor = (boolean) Micotian.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();

		float[] hue = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
		int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
		int red = (int) Micotian.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
		int green =  (int) Micotian.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
		int blue = (int) Micotian.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
		color = ColourUtils.toRGBA(red, green, blue, 195);
		int TheColor = setcolor ? ColourUtils.genRainbowShadow() : color;

		return TheColor;
	}
	public static float getColor3() {
		int color;
		boolean setcolor = (boolean) Micotian.instance.settingsManager.getSettingByName("Rainbow", "Range").getValBoolean();

		float[] hue = new float[]{(float) (System.currentTimeMillis() % 11520L) / 11520.0f};
		float rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
		float red = (float) Micotian.instance.settingsManager.getSettingByName("Red", "Range").getValDouble();
		float green =  (float) Micotian.instance.settingsManager.getSettingByName("Green", "Range").getValDouble();
		float blue = (float) Micotian.instance.settingsManager.getSettingByName("Blue", "Range").getValDouble();
		color = ColourUtils.toRGBA(red, green, blue, 255f);
		float TheColor = setcolor ? ColourUtils.genRainbowShadow() : color;

		return TheColor;
	}
	@Override
	public void onEnable() {
		String Mode = (String) Micotian.instance.settingsManager.getSettingByName(this, "Gui").getValString();
		switch (Mode){
			case "New":
				mc.displayGuiScreen(new Panel("Caesium", 22));
				break;
			case "Old":
				mc.displayGuiScreen(Micotian.instance.bobrGui);
				break;
			case "Castom":
				mc.displayGuiScreen(Micotian.instance.CastomGui);
				break;


		}

		super.onEnable();
		this.setToggled(false);

	}



}
