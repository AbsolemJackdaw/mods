package telepads.gui;

import net.minecraft.client.gui.GuiButton;

public class GuiTeleportButton extends GuiButton{

	public int xTeleport;
	public int yTeleport;
	public int zTeleport;
	public int dimTeleport;
	
	public GuiTeleportButton(int par1, int par2, int par3, int par4, int par5,
			String par6Str, int x, int y, int z, int dim) {
		super(par1, par2, par3, par4, par5, par6Str);
	
		x= xTeleport;
		y = yTeleport;
		z = zTeleport;
		dim = dimTeleport;
	
	}

}
