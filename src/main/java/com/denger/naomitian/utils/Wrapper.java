package com.denger.naomitian.utils;

import com.denger.naomitian.Micotian;
import net.minecraft.client.Minecraft;

public interface Wrapper {

	Minecraft mc = Minecraft.getMinecraft();

	default boolean nullCheck() {
		return mc.player != null || mc.world != null;
	}

	default Micotian getCosmos() {
		return Micotian.instance;
	}
}
