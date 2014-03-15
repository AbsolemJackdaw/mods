package threeDitems.handlers;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import threeDitems.mod_3d;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.TickType;

public class KeyHandler extends KeyBindingRegistry.KeyHandler {


	public KeyHandler() {
		super(new KeyBinding[]{new KeyBinding("3D Item Rendering", Keyboard.KEY_P)},
				new boolean[]{false, false});
	}

	@Override
	public String getLabel() {
		return "3D Item Rendering Key";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (!tickEnd)
			return;
		if(kb.keyDescription.equals("3D Item Rendering")){
			FMLLog.getLogger().info("up "+ kb.keyDescription);

			if(mod_3d.inst.isRendering3D)
				try {
					mod_3d.inst.isRendering3D = false;
				}catch (Throwable e) {
					FMLLog.getLogger().info("Failed to switch 3d mode !");
				}
			else
				try {
					mod_3d.inst.isRendering3D = true;
				}catch (Throwable e) {
					FMLLog.getLogger().info("Failed to switch 3d mode !");
				}
		}
		if(kb.keyDescription.equals("Bow Hud"))
			if(mod_3d.inst.showHud)
				try {
					mod_3d.inst.showHud = false;
				}catch (Throwable e) {
					FMLLog.getLogger().info("Failed to toggle hud !");
				}
			else
				try {
					mod_3d.inst.showHud = true;
				}catch (Throwable e) {
					FMLLog.getLogger().info("Failed to toggle hud!");
				}



	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}
