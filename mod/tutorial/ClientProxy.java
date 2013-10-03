package tutorial;

import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends CommonProxy {

	private static final tutarm tutChest = new tutarm(0.8f);
	private static final tutarm tutLegs = new tutarm(0.5f); 

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
