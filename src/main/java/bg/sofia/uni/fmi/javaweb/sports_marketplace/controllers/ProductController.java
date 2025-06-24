package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductCreateDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductSummaryDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.product.ProductUpdateDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        try {
            ProductDto product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductSummaryDto>> getAllProducts() {
        List<ProductSummaryDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ProductSummaryDto>> getProductsByStatus(@RequestParam String status) {
        List<ProductSummaryDto> products = productService.getProductsByStatus(status);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<UUID> createProduct(@RequestBody ProductCreateDto dto) {
        UUID productId = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateDto dto) {
        try {
            productService.updateProduct(id, dto);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductSummaryDto>> filterProducts(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID sportId,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) String status
    ) {
        List<ProductSummaryDto> products = productService.filterProducts(minPrice, maxPrice, categoryId, sportId, condition, status);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
