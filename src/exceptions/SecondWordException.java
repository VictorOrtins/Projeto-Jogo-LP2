package src.exceptions;
public class SecondWordException extends Exception {
    public SecondWordException(String s){
        super(s);
    }

    public SecondWordException(){
        
    }

    public SecondWordException(String s, Throwable err){
        super(s, err);
    }
}
