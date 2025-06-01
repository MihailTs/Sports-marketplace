package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class NoSuchForumPostException extends RuntimeException{
    public NoSuchForumPostException(){
        super("Post doesn't exist");
    }
}
