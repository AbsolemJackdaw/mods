package gravestone.handelers;

import gravestone.mod_Gravestone;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class CommandPanel extends CommandBase {

	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
		return true;
	}

	@Override
	public String getCommandName() {
		return "grave";
	}

	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] args) {
		List<String> returnList = new ArrayList();
		if (args == null){
			returnList.add("set");
		}
		else if (args.length == 1)
		{
			if(args[0].trim().length() == 0)
			{
				returnList.add("set");
			}
			else if("set".startsWith(args[0].toLowerCase().trim()))
			{
				returnList.add("set");
			}
		}
		else if (args.length == 2)
		{
			if(args[1].trim().length() == 0)
			{
				returnList.add("meta");
			}
			else if("meta".startsWith(args[1].toLowerCase().trim()))
			{
				returnList.add("meta");
			}
			else if("name".startsWith(args[1].toLowerCase().trim()))
			{
				returnList.add("name");
			}
			else if("message".startsWith(args[1].toLowerCase().trim()))
			{
				returnList.add("message");
			}
		}
		return returnList;
	}

	public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return " set + <[name(name of player)] or [message(death message)] or [meta(type of grave{1,2,3,4})]>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length > 0) {
			if (args[0].matches("set")) {

				if (args[1].matches("name"))
				{
					String name = args[2];
					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(sender.getCommandSenderName());
					if(player.getCurrentEquippedItem().getItem().equals(mod_Gravestone.graveItem))
					{
						NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();
						if(nbt != null)
						{
							nbt.setString("Name", name);
							player.getCurrentEquippedItem().setTagCompound(nbt);
						}
						else
						{
							NBTTagCompound nbt2 = new NBTTagCompound();
							nbt2.setString("Name", name);
							player.getCurrentEquippedItem().setTagCompound(nbt2);
						}
					}
					else
					{
						player.addChatMessage("You need to hold a Grave item.");
					}
				}	
				else if (args[1].matches("message"))
				{
					String name = args[2];
					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(sender.getCommandSenderName());
					if(player.getCurrentEquippedItem().getItem().equals(mod_Gravestone.graveItem))
					{
						NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();
						if(nbt != null)
						{
							nbt.setString("Message", name);
							player.getCurrentEquippedItem().setTagCompound(nbt);
						}
						else
						{
							NBTTagCompound nbt2 = new NBTTagCompound();
							nbt2.setString("Message", name);
							player.getCurrentEquippedItem().setTagCompound(nbt2);
						}
					}
					else
					{
						player.addChatMessage("You need to hold a Grave item.");
					}
				}
				else if (args[1].matches("message2"))
				{
					String name = args[2];
					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(sender.getCommandSenderName());
					if(player.getCurrentEquippedItem().getItem().equals(mod_Gravestone.graveItem))
					{
						NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();
						if(nbt != null)
						{
							nbt.setString("Message2", name);
							player.getCurrentEquippedItem().setTagCompound(nbt);
						}
						else
						{
							NBTTagCompound nbt2 = new NBTTagCompound();
							nbt2.setString("Message2", name);
							player.getCurrentEquippedItem().setTagCompound(nbt2);
						}
					}
					else
					{
						player.addChatMessage("You need to hold a Grave item.");
					}
				}
				else if (args[1].matches("meta")) {

					int level = parseIntWithMin(sender, args[2], 1);
					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(sender.getCommandSenderName());
					if(player.getCurrentEquippedItem().getItem().equals(mod_Gravestone.graveItem))
					{
						NBTTagCompound nbt = player.getCurrentEquippedItem().getTagCompound();

						if(nbt != null)
						{
							nbt.setInteger("Meta", level);
							player.getCurrentEquippedItem().setTagCompound(nbt);
						}
						else
						{
							NBTTagCompound nbt2 = new NBTTagCompound();
							nbt2.setInteger("Meta", level);
							player.getCurrentEquippedItem().setTagCompound(nbt2);
						}
					}
					else
					{
						player.addChatMessage("You need to hold a Grave item.");
					}

				}	
			}
		}
	}
}
