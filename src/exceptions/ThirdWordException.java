package src.exceptions;
public class ThirdWordException extends Exception {
    public ThirdWordException(String s){
        super(s);
    }

    public ThirdWordException(){
        
    }

    public ThirdWordException(String s, Throwable err){
        super(s, err);
    }
}