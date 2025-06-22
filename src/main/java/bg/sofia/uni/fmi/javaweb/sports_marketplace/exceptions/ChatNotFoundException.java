package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

public class ChatNotFoundException extends RuntimeException{
    public ChatNotFoundException(){
        super("Chat was not found");
    }
}
