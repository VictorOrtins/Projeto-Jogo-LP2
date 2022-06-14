package src.exceptions;
public class MonsterAliveException extends Exception {
    public MonsterAliveException(String s){
        super(s);
    }

    public MonsterAliveException(){
        
    }

    public MonsterAliveException(String s, Throwable err){
        super(s, err);
    }
}
