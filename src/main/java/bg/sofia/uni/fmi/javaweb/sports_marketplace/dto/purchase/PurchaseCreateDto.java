package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.purchase;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.purchase_item.PurchaseItemCreateDto;

import java.util.List;

public record PurchaseCreateDto(
        Long supplierId,
        List<PurchaseItemCreateDto> items
) {}
