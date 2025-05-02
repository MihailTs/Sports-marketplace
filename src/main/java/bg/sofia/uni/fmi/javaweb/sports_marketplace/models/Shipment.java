package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carrier;
    private String trackingNumber;
    private String status;
    private LocalDateTime shippingDate;
    private LocalDateTime deliveryDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
