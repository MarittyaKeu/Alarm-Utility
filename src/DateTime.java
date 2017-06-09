
public class DateTime<E, T, K, M, I> {
	E dates;
	T dName;
	K sub;
	M messages;
	I ids;
	public DateTime(E date, T time, K subject, M message, I id){
		dates = date;
		dName = time;
		sub = subject;
		messages = message;
		ids = id;
	}
	
	public E getDate(){
		return dates;
	}
	
	public T getTime(){
		return dName;
	}
	
	public K getSubject(){
		return sub;
	}
	
	public M getMessage(){
		return messages;
	}
	
	public I getID(){
		return ids;
	}
	
	
}