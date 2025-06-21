package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductSummaryDto {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String status;
    private String condition;
}
