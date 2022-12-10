package com.denger.naomitian.module;


import com.denger.naomitian.module.misc.SelfDestruct;
import com.denger.naomitian.notifications.Type;
import com.denger.naomitian.notifications.notifications;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;

public abstract class Module {

	protected static Minecraft mc = Minecraft.getMinecraft();

	private String name, description;
	private int key;
	private Category category;
	private boolean toggled;
	public boolean visible = true;

	public Module(String name, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.key = 0;
		this.category = category;
		this.toggled = false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		if (!SelfDestruct.self){
			if (this.toggled) {
				this.onEnable();
			} else {
				this.onDisable();
			}
		}

	}

	public void toggle() {
		this.toggled = !this.toggled;
		if (!SelfDestruct.self){
			if (this.toggled) {
				this.onEnableR();
				//this.onEnable();
			} else {
				this.onDisableR();
				this.onDisable();
			}
		}

	}



	public void onEnable() {

		MinecraftForge.EVENT_BUS.register(this);

		notifications.add(this.name, TextFormatting.GREEN + "Enable!", Type.Green);
		//notif.add(this.name,  TextFormatting.GREEN + "Enable!", cf.vaccat.catclient.noti.Category.INFO);
	}



	public void onEnableR() {
		onEnable();
	}
	public void onDisableR() {

		onDisable();
	}

	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
		notifications.add(this.name, TextFormatting.RED + "Disable!", Type.Red);
		//notif.add(this.name,  TextFormatting.RED + "Disable!", cf.vaccat.catclient.noti.Category.INFO);

	}

	public String getName() {
		return this.name;
	}

	public Category getCategory() {
		return this.category;
	}

	protected void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
	}
}
