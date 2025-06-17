package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.*;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchProductException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchCategoryException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.*;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchProductException(id));
    }

    public Page<Product> getProductsByCategory(UUID categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<Product> getProductsByStatus(ProductStatus status, Pageable pageable) {
        return productRepository.findByStatus(status, pageable);
    }

    public Page<Product> getProductsByCategory(UUID categoryId, ProductStatus status, Pageable pageable) {
        return status != null ?
            productRepository.findByCategoryIdAndStatus(categoryId, status, pageable) :
            productRepository.findByCategoryId(categoryId, pageable);
    }

    @Transactional
    public Product createProduct(ProductCreateDto productCreateDto, String sellerEmail) {
        User seller = userRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new UserDoesntExistException());

        Category category = categoryRepository.findById(productCreateDto.categoryId())
                .orElseThrow(() -> new NoSuchCategoryException(productCreateDto.categoryId()));

        Product product = new Product();
        product.setName(productCreateDto.name());
        product.setDescription(productCreateDto.description());
        product.setPrice(productCreateDto.price());
        product.setCondition(productCreateDto.condition());
        product.setCategory(category);
        product.setSeller(seller);
        product.setStatus(ProductStatus.AVAILABLE);
        product.setVariants(new ArrayList<>());
        product.setImages(new ArrayList<>());

        product = productRepository.save(product);

        if (productCreateDto.variants() != null) {
            for (ProductVariantDto variantDto : productCreateDto.variants()) {
                ProductVariant variant = variantDto.toEntity();
                variant.setProduct(product);
                product.getVariants().add(variant);
            }
        }

        if (productCreateDto.imageUrls() != null) {
            boolean isPrimarySet = false;
            for (String imageUrl : productCreateDto.imageUrls()) {
                ProductImage image = new ProductImage();
                image.setProduct(product);
                image.setUrl(imageUrl);
                image.setIsPrimary(!isPrimarySet);
                isPrimarySet = true;
                product.getImages().add(image);
            }
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(UUID id, ProductUpdateDto productUpdateDto) {
        Product product = getProductById(id);

        if (productUpdateDto.name() != null) {
            product.setName(productUpdateDto.name());
        }
        if (productUpdateDto.description() != null) {
            product.setDescription(productUpdateDto.description());
        }
        if (productUpdateDto.price() != null) {
            product.setPrice(productUpdateDto.price());
        }
        if (productUpdateDto.categoryId() != null) {
            Category category = categoryRepository.findById(productUpdateDto.categoryId())
                    .orElseThrow(() -> new NoSuchCategoryException(productUpdateDto.categoryId()));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    @Transactional
    public ProductVariant addProductVariant(UUID productId, ProductVariantDto variantDto) {
        Product product = getProductById(productId);
        
        ProductVariant variant = variantDto.toEntity();
        variant.setProduct(product);
        
        product.getVariants().add(variant);
        productRepository.save(product);
        
        return variant;
    }

    @Transactional
    public ProductImage addProductImage(UUID productId, String imageUrl, Boolean isPrimary) {
        Product product = getProductById(productId);
        
        if (isPrimary) {
            // Reset primary flag for all existing images
            product.getImages().forEach(img -> img.setIsPrimary(false));
        }
        
        ProductImage image = new ProductImage();
        image.setProduct(product);
        image.setUrl(imageUrl);
        image.setIsPrimary(isPrimary);
        
        product.getImages().add(image);
        productRepository.save(product);
        
        return image;
    }

    @Transactional
    public void deleteProductVariant(UUID productId, UUID variantId) {
        Product product = getProductById(productId);
        product.getVariants().removeIf(variant -> variant.getId().equals(variantId));
        productRepository.save(product);
    }

    @Transactional
    public void deleteProductImage(UUID productId, UUID imageId) {
        Product product = getProductById(productId);
        product.getImages().removeIf(image -> image.getId().equals(imageId));
        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchProductException(id);
        }
        productRepository.deleteById(id);
    }

    public Page<Product> searchProducts(String query, ProductStatus status, Pageable pageable) {
        return status != null ?
            productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndStatus(query, query, status, pageable) :
            productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query, pageable);
    }

    @Transactional
    public Product updateProductStatus(UUID id, ProductStatus status) {
        Product product = getProductById(id);
        product.setStatus(status);
        return productRepository.save(product);
    }
} 