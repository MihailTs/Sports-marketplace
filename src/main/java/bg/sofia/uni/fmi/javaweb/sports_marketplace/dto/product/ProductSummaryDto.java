package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import java.util.UUID;

public record ProductSummaryDto (UUID id,
                                 UUID sellerId,
                                 String sellerName,
                                 String name,
                                 double price,
                                 String status,
                                 String description,
                                 String condition) {
    public static ProductSummaryDto fromEntity(Product product){
        return new ProductSummaryDto(
                product.getId(),
                product.getSeller().getId(),
                product.getSeller().getFirstName() + " " + product.getSeller().getLastName(),
                product.getName(),
                product.getPrice(),
                product.getStatus(),
                product.getDescription(),
                product.getCondition());
    }
}
