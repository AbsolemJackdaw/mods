package telepads;

import telepads.block.TETelepad;
import telepads.model.telepad;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemPadRenderer implements IItemRenderer {
	private telepad pad;

	public ItemPadRenderer() {
		pad = new telepad();
	}
	private static ResourceLocation loc = new ResourceLocation("subaraki:pad/telepad.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TETelepad(), 0.0D, 0.0D, 0.0D, 0.0F);

	}
}
