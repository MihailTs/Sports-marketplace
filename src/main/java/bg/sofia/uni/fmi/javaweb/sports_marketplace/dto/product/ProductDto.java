package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Category;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record ProductDto(
    UUID id,
    UserDto seller,
    String name,
    String description,
    UUID categoryId,
    String condition,
    BigDecimal price,
    ProductStatus status,
    List<ProductVariantDto> variants,
    List<ProductImageDto> images,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static ProductDto fromEntity(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");
        return new ProductDto(
            product.getId(),
            product.getSeller() != null ? UserDto.fromEntity(product.getSeller()) : null,
            product.getName(),
            product.getDescription(),
            product.getCategory() != null ? product.getCategory().getId() : null,
            product.getCondition(),
            product.getPrice(),
            product.getStatus(),
            product.getVariants() != null ? 
                product.getVariants().stream()
                    .map(ProductVariantDto::fromEntity)
                    .toList() : 
                List.of(),
            product.getImages() != null ? 
                product.getImages().stream()
                    .map(ProductImageDto::fromEntity)
                    .toList() : 
                List.of(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }

    public Product toEntity() {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCondition(condition);
        product.setStatus(status != null ? status : ProductStatus.AVAILABLE);
        
        if (variants != null) {
            List<ProductVariant> productVariants = new ArrayList<>();
            for (ProductVariantDto variantDto : variants) {
                ProductVariant variant = variantDto.toEntity();
                variant.setProduct(product);
                productVariants.add(variant);
            }
            product.setVariants(productVariants);
        }

        if (images != null) {
            List<ProductImage> productImages = new ArrayList<>();
            for (ProductImageDto imageDto : images) {
                ProductImage image = imageDto.toEntity();
                image.setProduct(product);
                productImages.add(image);
            }
            product.setImages(productImages);
        }

        return product;
    }
}
