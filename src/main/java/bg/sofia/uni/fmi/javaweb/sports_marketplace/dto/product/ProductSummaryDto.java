package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum.ForumCommentDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumComment;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductSummaryDto (UUID id, String name, double price, String status, String condition) {
    public static ProductSummaryDto fromEntity(Product product){
        return new ProductSummaryDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStatus(),
                product.getCondition());
    }
}
