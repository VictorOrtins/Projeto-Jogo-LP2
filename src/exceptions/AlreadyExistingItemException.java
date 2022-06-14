package src.exceptions;
public class AlreadyExistingItemException extends Exception {
    public AlreadyExistingItemException(String s){
        super(s);
    }

    public AlreadyExistingItemException(){
        
    }

    public AlreadyExistingItemException(String s, Throwable err){
        super(s, err);
    }
}