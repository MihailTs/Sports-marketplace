package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class NoSuchForumCommentException extends RuntimeException{
    public NoSuchForumCommentException(){
        super("Forum comment doesn't exist.");
    }
}
