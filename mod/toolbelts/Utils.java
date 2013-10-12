package toolbelts;

public class Utils {


	public static String beltIDToName(int id){

		switch(id){
		case 0 :
			return "Default Belt";
		case 1 :
			return "Rambo Belt";
		case 2 :
			return "Assassin's Belt";
		case 3:
			return "Simple Shoulder Bag";
		case 4 :
			return "HotBar Belt";
		case 5 :
			return "Survivor Kit";
		case 6:
			return "IChun Rip-off Belt";
		default : 
			return "Unsuported Belt ID";
		}
	}
}
