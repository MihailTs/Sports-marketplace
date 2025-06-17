package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.*;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductImage;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductStatus;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ProductVariant;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @RequestParam(required = false) ProductStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<Product> products = status != null ? 
            productService.getProductsByStatus(status, pageable) :
            productService.getAllProducts(pageable);
        return ResponseEntity.ok(products.map(ProductDto::fromEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductDto.fromEntity(product));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(
            @Valid @RequestBody ProductCreateDto productCreateDto,
            Authentication authentication) {
        Product product = productService.createProduct(productCreateDto, authentication.getName());
        return ResponseEntity.ok(ProductDto.fromEntity(product));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@productSecurityService.isProductOwner(#id, authentication.name) or hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody ProductUpdateDto productUpdateDto) {
        Product product = productService.updateProduct(id, productUpdateDto);
        return ResponseEntity.ok(ProductDto.fromEntity(product));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@productSecurityService.isProductOwner(#id, authentication.name) or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDto>> searchProducts(
            @RequestParam String query,
            @RequestParam(required = false) ProductStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(productService.searchProducts(query, status, pageable)
                .map(ProductDto::fromEntity));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductDto>> getProductsByCategory(
            @PathVariable UUID categoryId,
            @RequestParam(required = false) ProductStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId, status, pageable)
                .map(ProductDto::fromEntity));
    }

    @PostMapping("/{id}/variants")
    @PreAuthorize("@productSecurityService.isProductOwner(#id, authentication.name) or hasRole('ADMIN')")
    public ResponseEntity<ProductVariantDto> addProductVariant(
            @PathVariable UUID id,
            @Valid @RequestBody ProductVariantCreateDto variantDto) {
        ProductVariant variant = productService.addProductVariant(id, variantDto);
        return ResponseEntity.ok(ProductVariantDto.fromEntity(variant));
    }

    @DeleteMapping("/{productId}/variants/{variantId}")
    @PreAuthorize("@productSecurityService.isProductOwner(#productId, authentication.name) or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProductVariant(
            @PathVariable UUID productId,
            @PathVariable UUID variantId) {
        productService.deleteProductVariant(productId, variantId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/images")
    @PreAuthorize("@productSecurityService.isProductOwner(#id, authentication.name) or hasRole('ADMIN')")
    public ResponseEntity<ProductImageDto> addProductImage(
            @PathVariable UUID id,
            @Valid @RequestBody ProductImageDto imageDto) {
        ProductImage image = productService.addProductImage(id, imageDto.url(), imageDto.isPrimary());
        return ResponseEntity.ok(ProductImageDto.fromEntity(image));
    }

    @DeleteMapping("/{productId}/images/{imageId}")
    @PreAuthorize("@productSecurityService.isProductOwner(#productId, authentication.name) or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProductImage(
            @PathVariable UUID productId,
            @PathVariable UUID imageId) {
        productService.deleteProductImage(productId, imageId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("@productSecurityService.isProductOwner(#id, authentication.name) or hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProductStatus(
            @PathVariable UUID id,
            @RequestParam ProductStatus status) {
        Product product = productService.updateProductStatus(id, status);
        return ResponseEntity.ok(ProductDto.fromEntity(product));
    }
} 