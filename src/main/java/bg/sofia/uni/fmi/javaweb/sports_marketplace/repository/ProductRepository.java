package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByStatus(String status);

    List<Product> findBySellerId(UUID sellerId);

    List<Product> findByCategoryId(UUID categoryId);

    List<Product> findByPriceGreaterThanEqualOrderByPriceAsc(double price);

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE' ORDER BY p.createdAt DESC")
    List<Product> findRecentActiveProducts();

    @Query("""
        SELECT p FROM Product p
        WHERE (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        AND (:categoryId IS NULL OR p.category.id = :categoryId)
        AND (:sportId IS NULL OR p.sport.id = :sportId)
        AND (:condition IS NULL OR p.condition = :condition)
        AND (:status IS NULL OR p.status = :status)
    """)
    List<Product> filterProducts(
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("categoryId") UUID categoryId,
            @Param("sportId") UUID sportId,
            @Param("condition") String condition,
            @Param("status") String status
    );

}