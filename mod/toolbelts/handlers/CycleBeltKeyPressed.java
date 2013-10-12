package toolbelts.handlers;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import toolbelts.PlayerBeltTracker;
import toolbelts.mod_hipItems;
import toolbelts.handlers.packets.BeltPacket;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class CycleBeltKeyPressed extends KeyHandler{

	public CycleBeltKeyPressed() {
		super(new KeyBinding[]{new KeyBinding("Belt Cycle Key + Ctrl", Keyboard.KEY_ADD)}, new boolean[]{false, false});
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "ToolBelts Belt Alterner";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
	}

	int cycle = 0;
	
	private static boolean isCtrlKeyDown()
    {
        return Minecraft.isRunningOnMac ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
    }
	
	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		
		if(kb.keyDescription.equals("Belt Cycle Key + Ctrl") && isCtrlKeyDown() && tickEnd){

			EntityPlayer p = Minecraft.getMinecraft().thePlayer;

			if(cycle >= mod_hipItems.BELTID_MAX){
				cycle = mod_hipItems.BELTID_MIN;
			}else{
				cycle++;
			}

			if(PlayerBeltTracker.get(p).BeltID >= mod_hipItems.BELTID_MAX){
				PlayerBeltTracker.get(p).BeltID = mod_hipItems.BELTID_MIN;
			}else{
				PlayerBeltTracker.get(p).BeltID++;
			}
			
			BeltPacket.sendServerBeltId(p);
		}
	}
}