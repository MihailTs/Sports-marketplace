package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

import java.util.UUID;

public class TooManyProductImagesException extends RuntimeException {
    public TooManyProductImagesException(UUID productId) {
        super("Product with ID " + productId + " already has the maximum allowed number of images (25)");
    }
} 