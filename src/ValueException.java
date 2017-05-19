
public class ValueException extends Exception {
	
	
	  public ValueException(){
	        this("Value can not be null");
	    }
	    
	    public ValueException(String message){
	        super(message);
	    }
	    
}
