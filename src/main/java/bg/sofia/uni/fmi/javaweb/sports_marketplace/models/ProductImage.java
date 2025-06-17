package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "product_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "is_primary")
    private Boolean isPrimary;
} 