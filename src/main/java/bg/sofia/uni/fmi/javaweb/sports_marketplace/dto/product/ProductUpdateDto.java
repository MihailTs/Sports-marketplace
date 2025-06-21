package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductUpdateDto {
    private String name;
    private String description;
    private UUID categoryId;
    private String condition;
    private BigDecimal price;
    private String status;
}
