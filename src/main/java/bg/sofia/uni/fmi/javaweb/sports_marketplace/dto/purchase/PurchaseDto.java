package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.purchase;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.purchase_item.PurchaseItemDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.supplier.SupplierDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PurchaseDto(
        Long id,
        LocalDateTime purchaseDate,
        String status,
        BigDecimal totalAmount,
        SupplierDto supplier,
        List<PurchaseItemDto> items
) {
    public static PurchaseDto fromEntity(Purchase purchase) {
        return new PurchaseDto(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getStatus(),
                purchase.getTotalAmount(),
                purchase.getSupplier() != null ?
                        SupplierDto.fromEntity(purchase.getSupplier()) : null,
                purchase.getPurchaseItems() != null ?
                        purchase.getPurchaseItems().stream()
                                .map(PurchaseItemDto::fromEntity)
                                .collect(Collectors.toList()) :
                        List.of()
        );
    }
}