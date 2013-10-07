package vanilla3DArmor;

import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends CommonProxy {

	private static final VanillaArmorModel tutChest = new VanillaArmorModel(1f);
	private static final VanillaArmorModel tutLegs = new VanillaArmorModel(0.5f); 

	@Override
	    public ModelBiped getArmorModel(int id){
	        switch (id) {
	        case 0:
	            return tutChest;
	        case 1:
	            return tutLegs;
	        default:
	            break;
	        }
	        return tutChest; //default, if whenever you should have passed on a wrong id
	    }
}
