/*
 ** 2012 August 24
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon;

import java.util.List;

import scythemod.dragon.entity.DragonEntity;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class CommandDragon extends CommandBase {

    @Override
    public String getCommandName() {
        return "dragon";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params) {
        if (params.length >= 1 && params[0].length() > 0) {
            String command = params[0];
            EntityPlayerMP player = (EntityPlayerMP) getCommandSenderAsPlayer(sender);
            
            List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player,
                    player.boundingBox.expand(128, 16, 128));

            for (int i = 0; i < entities.size(); i++) {
                Entity entity = entities.get(i);
                
                if (!(entity instanceof DragonEntity)) {
                    continue;
                }
                
               
            }
        } else {
            throw new WrongUsageException("commands.dragon.usage");
        }
    }

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return null;
	}
    
}
