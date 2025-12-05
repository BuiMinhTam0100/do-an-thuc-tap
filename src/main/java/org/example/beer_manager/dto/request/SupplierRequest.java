package org.example.beer_manager.dto.request;

public record SupplierRequest(
        String supplierName,
        String phone,
        String address
) {
}
