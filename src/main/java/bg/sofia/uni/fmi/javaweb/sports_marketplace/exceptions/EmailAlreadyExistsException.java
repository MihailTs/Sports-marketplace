package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email){
        super("Email "+email+" already exists");
    }
}
