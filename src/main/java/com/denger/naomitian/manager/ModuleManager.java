package com.denger.naomitian.manager;

import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import com.denger.naomitian.module.hud.*;
import com.denger.naomitian.module.combat.*;
import com.denger.naomitian.module.misc.*;
import com.denger.naomitian.module.movement.GuiWalk;
import com.denger.naomitian.module.movement.Sprint;
import com.denger.naomitian.module.movement.strafe;
import com.denger.naomitian.module.render.*;
import com.google.common.reflect.ClassPath;

import java.util.ArrayList;
import java.util.Iterator;

public class ModuleManager {

	public ArrayList<Module> modules;

	public ModuleManager() {
		(modules = new ArrayList<Module>()).clear();
		this.modules.add(new AimBot());
		this.modules.add(new AimAssist());
		this.modules.add(new ClickGUI());
		this.modules.add(new Sprint());
		this.modules.add(new AntiBot());
		this.modules.add(new ESP());
		this.modules.add(new Tracers());
		this.modules.add(new GuiWalk());
		this.modules.add(new FullBright());
		this.modules.add(new NameTags());
		this.modules.add(new strafe());
		this.modules.add(new WallHackPlayer());
		this.modules.add(new WallHack());
		this.modules.add(new ArmorHUD());
		this.modules.add(new TriggerBot());
		this.modules.add(new ModuleList());
		this.modules.add(new ViewModel());
		this.modules.add(new Notifications());
		this.modules.add(new FakePlayer());
		this.modules.add(new MiddleClick());
		this.modules.add(new ChinaHat());
		this.modules.add(new DeathCoords());
		this.modules.add(new AutoArmor());
		this.modules.add(new Watermark());
		this.modules.add(new TEST());
		this.modules.add(new AutoClicker());

		for (Module m : this.modules) {
		}
	}

	public static ArrayList<Class<?>> getClasses(final String packageName) {
		final ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		try {
			final ClassLoader loader = Thread.currentThread().getContextClassLoader();
			for (final ClassPath.ClassInfo info : ClassPath.from(loader).getAllClasses()) {
				if (info.getName().startsWith(packageName)) {
					classes.add(info.load());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}



	public Module getModule(String name) {
		for (Module m : this.modules) {
			if (m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

	public ArrayList<Module> getModuleList() {
		return this.modules;
	}

	public ArrayList<Module> getModulesInCategory(Category c) {
		ArrayList<Module> mods = new ArrayList<Module>();
		for (Module m : this.modules) {
			if (m.getCategory() == c) {
				mods.add(m);
			}
		}
		return mods;
	}


	public ArrayList<Module> getModules(Category category) {
		ArrayList<Module> modules2 = new ArrayList();
		Iterator var3 = this.modules.iterator();

		while(var3.hasNext()) {
			Module module = (Module)var3.next();
			if (module.getCategory() == category) {
				modules2.add(module);
			}
		}

		return modules2;
	}
}
