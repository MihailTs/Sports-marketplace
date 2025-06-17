package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchProductException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UnAuthorizedAccessException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductSecurityService {
    private final ProductRepository productRepository;

    public boolean isProductOwner(UUID productId, String userEmail) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NoSuchProductException(productId));
            
        if (!product.getSeller().getEmail().equals(userEmail)) {
            throw new UnAuthorizedAccessException();
        }
        
        return true;
    }
} 