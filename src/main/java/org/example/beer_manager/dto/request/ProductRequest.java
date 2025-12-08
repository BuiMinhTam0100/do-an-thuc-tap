package org.example.beer_manager.dto.request;

import java.math.BigDecimal;

public record ProductRequest(
        Integer categoryID,
        String categoryName,
        String productName,
        BigDecimal price,
        String unit,
        Integer quantity
) {
}
