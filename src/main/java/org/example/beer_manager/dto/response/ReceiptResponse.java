package org.example.beer_manager.dto.response;
import java.math.BigDecimal;
import java.time.Instant;

public record ReceiptResponse(
        Integer receiptId,
        Integer supplierId,
        String supplierName,
        BigDecimal totalAmount,
        Instant deletedAt
) {
}
