package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class TimeframeMismatchException extends RuntimeException{
    public TimeframeMismatchException(){
        super("Impossible time interval");
    }
}
