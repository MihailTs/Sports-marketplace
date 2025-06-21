package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductCreateDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductUpdateDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Category;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.CategoryRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ProductRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return toFetchDto(product);
    }

    public List<ProductSummaryDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    public List<ProductSummaryDto> getProductsByStatus(String status) {
        return productRepository.findByStatus(status).stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    public UUID createProduct(ProductCreateDto dto) {
        System.out.println("AUAUUAUAUAUUAUAUAUA  " + dto.getSellerId());
        User seller = userRepository.findById(dto.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .description(dto.getDescription())
                .seller(seller)
                .category(category)
                .condition(dto.getCondition())
                .price(dto.getPrice())
                .status(dto.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        productRepository.save(product);
        return product.getId();
    }

    public void updateProduct(UUID id, ProductUpdateDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategory(category);
        product.setCondition(dto.getCondition());
        product.setPrice(dto.getPrice());
        product.setStatus(dto.getStatus());
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private ProductSummaryDto toSummaryDto(Product product) {
        ProductSummaryDto dto = new ProductSummaryDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCondition(product.getCondition());
        dto.setStatus(product.getStatus());
        return dto;
    }

    private ProductDto toFetchDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setSellerId(product.getSeller().getId());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCondition(product.getCondition());
        dto.setPrice(product.getPrice());
        dto.setStatus(product.getStatus());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }
}
