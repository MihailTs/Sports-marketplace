package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Category;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @Column(length = 255)
    private String condition;

    @Column(precision = 2)
    private double price;

    @Column(length = 255)
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
