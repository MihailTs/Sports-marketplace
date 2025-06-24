package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private UUID sellerId;
    private UUID categoryId;
    private String condition;
    private double price;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
