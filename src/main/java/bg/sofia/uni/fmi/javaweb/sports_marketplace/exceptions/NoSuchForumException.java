package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class NoSuchForumException extends RuntimeException{
    public NoSuchForumException(){
        super("Forum doesn't exist");
    }
}
