package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class WrongEmailOrPasswordException extends RuntimeException{
    public WrongEmailOrPasswordException(){
        super("Wrong email or password");
    }
}
