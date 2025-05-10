package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto;

import java.util.List;
import java.util.stream.Collectors;

public record PagedResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public static <T, E> PagedResponse<T> fromPage(
            org.springframework.data.domain.Page<E> page,
            java.util.function.Function<E, T> converter) {
        List<T> content = page.getContent().stream()
                .map(converter)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
