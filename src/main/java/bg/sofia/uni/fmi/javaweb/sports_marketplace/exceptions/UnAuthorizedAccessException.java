package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class UnAuthorizedAccessException extends RuntimeException{
    public UnAuthorizedAccessException(){
        super("cant edit or delete other profiles.");
    }
}
