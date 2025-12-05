package org.example.beer_manager.dto.response;

import java.time.Instant;

public record CategoriesResponse(
        Integer id,
        String categoryName,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
}
