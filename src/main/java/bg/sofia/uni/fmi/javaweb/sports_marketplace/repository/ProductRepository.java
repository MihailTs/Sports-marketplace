package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByStatus(String status);

    List<Product> findBySellerId(UUID sellerId);

    List<Product> findByCategoryId(UUID categoryId);

    List<Product> findByPriceGreaterThanEqualOrderByPriceAsc(java.math.BigDecimal price);

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE' ORDER BY p.createdAt DESC")
    List<Product> findRecentActiveProducts();

}