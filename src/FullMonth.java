
public class FullMonth<E, T> {
	E days;
	T dName;
	public FullMonth(E day, T dayName){
		days = day;
		dName = dayName;
	}
	
	public E getDay(){
		return days;
	}
	
	public T getDayName(){
		return dName;
	}
	
}
