package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

import java.util.UUID;

public class NoSuchCategoryException extends RuntimeException {
    public NoSuchCategoryException(UUID id) {
        super("Category not found with id: " + id);
    }
    
    public NoSuchCategoryException() {
        super("Category not found");
    }
} 