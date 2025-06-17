package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(UUID id) {
        super("Product not found with id: " + id);
    }
    
    public NoSuchProductException() {
        super("Product not found");
    }
} 