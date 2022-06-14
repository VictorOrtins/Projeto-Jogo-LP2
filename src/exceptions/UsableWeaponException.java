package src.exceptions;
public class UsableWeaponException extends Exception {
    public UsableWeaponException(String s){
        super(s);
    }

    public UsableWeaponException(){
        
    }

    public UsableWeaponException(String s, Throwable err){
        super(s, err);
    }
}