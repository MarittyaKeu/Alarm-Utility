
public class FormatCheck {
	public FormatCheck(){
		
	}
	
	//Format
	
	public boolean checkTime(String hourStr, String minStr){
		//checks if string contains numbers
		if(hourStr.matches("[0-9]+") && minStr.matches("[0-9]+")) {
			//parses string into int
			int newHourStr = Integer.parseInt(hourStr);
			int newMinStr = Integer.parseInt(minStr);
			//checks if input time is valid
			if(newHourStr >= 1 && newHourStr <= 12 && newMinStr >= 0 && newMinStr <= 59)
				return true;
		}
		
		return false;
	}
	
	
	public boolean checkSubLength(String str) {
		if(str.length() <= 255) {
			return true;
		}
		
		return false;
	}
	
}