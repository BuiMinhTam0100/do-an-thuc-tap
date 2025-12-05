package org.example.beer_manager.dto.response;

import java.time.Instant;

public record SupplierResponse(
        Long id,
        String supplierName,
        String phone,
        String address,
        Instant deletedAt
) {
}
