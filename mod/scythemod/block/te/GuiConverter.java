package scythemod.block.te;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class GuiConverter extends GuiContainer
{
	private TileEntityConverter goldInventory;
	 
    public GuiConverter(InventoryPlayer inventory, TileEntityConverter gold)
    {
        super(new ContainerConverter(inventory, gold));
        goldInventory = gold;
    }
 
    /**
     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString(StatCollector.translateToLocal(""), 8, (ySize - 96) + 2, 0xffffff);
    }
 
    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture("/DSM/DeathConverter.png");
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
        int burn = this.goldInventory.getCookProgressScaled(30);
        int burn2 = this.goldInventory.getCookProgressScaled(20);
        int burn3 = this.goldInventory.getCookProgressScaled(154);
        int burn4 = this.goldInventory.getBurnTimeRemainingScaled(154);

 
//        if (!this.goldInventory.isBurning())
//        {
            drawTexturedModalRect(j+37, k+32+(27-burn), j+51, k-17, 50, burn);  // big tank
            drawTexturedModalRect(j+82, k+19+(18-burn2), j-60, k+130, 22 ,burn2); // small tank
            drawTexturedModalRect(j+11, k+5, 0, 199, burn3, 7);  // complete bar

//        }
    
    }
}
