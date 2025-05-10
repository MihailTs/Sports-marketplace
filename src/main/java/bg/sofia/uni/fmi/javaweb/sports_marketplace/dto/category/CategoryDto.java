package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.category;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Category;

public record CategoryDto(
        Long id,
        String name,
        String description
) {
    public static CategoryDto fromEntity(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}