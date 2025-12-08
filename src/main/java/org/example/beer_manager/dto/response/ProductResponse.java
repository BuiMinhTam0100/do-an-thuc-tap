package org.example.beer_manager.dto.response;
import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        Long id,
        Long categoryID,
        String categoryName,
        String productName,
        BigDecimal price,
        String unit,
        Integer quantity,
        Byte isActive,
        Instant deletedAt
) {
}
