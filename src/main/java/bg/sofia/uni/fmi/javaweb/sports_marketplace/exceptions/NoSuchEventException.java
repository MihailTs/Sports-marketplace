package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class NoSuchEventException extends RuntimeException{
    public NoSuchEventException(){
        super("No such event exists");
    }
}
