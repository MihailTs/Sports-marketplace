package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductUpdateDto {
    private String name;
    private String description;
    private UUID categoryId;
    private String condition;
    private double price;
    private String status;
}
