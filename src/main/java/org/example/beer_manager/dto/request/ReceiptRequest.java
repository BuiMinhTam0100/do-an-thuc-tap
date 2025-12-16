package org.example.beer_manager.dto.request;

import java.math.BigDecimal;

public record ReceiptRequest(
        Integer supplierId,
        String supplierName,
        BigDecimal totalAmount
) {
}
