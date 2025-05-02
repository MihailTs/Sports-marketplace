package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.shipment;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Shipment;

import java.time.LocalDateTime;

public record ShipmentDto(
        Long id,
        String carrier,
        String trackingNumber,
        String status,
        LocalDateTime shippingDate,
        LocalDateTime deliveryDate
) {
    public static ShipmentDto fromEntity(Shipment shipment) {
        return new ShipmentDto(
                shipment.getId(),
                shipment.getCarrier(),
                shipment.getTrackingNumber(),
                shipment.getStatus(),
                shipment.getShippingDate(),
                shipment.getDeliveryDate()
        );
    }
}
