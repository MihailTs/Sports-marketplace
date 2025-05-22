package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class UserDoesntExistException extends RuntimeException{
    public UserDoesntExistException(){
        super("No such user in the database");
    }
}
