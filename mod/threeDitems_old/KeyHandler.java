package threeDitems_old;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;

public class KeyHandler extends KeyBindingRegistry.KeyHandler {


	public KeyHandler() {
		super(new KeyBinding[]{new KeyBinding("3D Item Rendering", Keyboard.KEY_P)}, new boolean[]{false, false});
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (!tickEnd) {
			return;
		}
		if(mod_3d.inst.isRendering3D){
			try {
				mod_3d.inst.isRendering3D = false;
			}catch (Throwable e) {
			}
		}else{
			try {
				mod_3d.inst.isRendering3D = true;
			}catch (Throwable e) {
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "3D Item Rendering Key";
	}
}
