
public class ValueException extends Exception {
	
	
	  public ValueException(){
	        this("Subject, Sound, Date, and Time can not be null");
	    }
	    
	    public ValueException(String message){
	        super(message);
	    }
	    
}
