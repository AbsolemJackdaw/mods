package gravestone.handelers;

import gravestone.mod_Gravestone;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;

public class KeyHandler extends KeyBindingRegistry.KeyHandler {


	public KeyHandler() {
		super(new KeyBinding[]{new KeyBinding("ModelKey", Keyboard.KEY_M)}, new boolean[]{false, false});

	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		//
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (!tickEnd) {
			return;
		}
		try {
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen guiscreen = mc.currentScreen;
			if (kb.keyDescription.equals("ModelKey")) {
				if (guiscreen == null) {
							mod_Gravestone.proxy.openGui2(1, mc.thePlayer);
				}

			}
		}catch (Throwable e) {
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "Grave Model Key";
	}
}
