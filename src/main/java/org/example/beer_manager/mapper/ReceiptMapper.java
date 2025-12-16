package org.example.beer_manager.mapper;
import org.example.beer_manager.dto.request.ReceiptRequest;
import org.example.beer_manager.dto.response.ReceiptResponse;
import org.example.beer_manager.entity.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReceiptMapper {

    @Mapping(source = "supplierName", target = "supplier.supplierName")
    @Mapping(source = "supplierId", target = "supplier.id")
    Receipt toEntity(ReceiptRequest request);

    @Mapping(source = "supplier.supplierName", target = "supplierName")
    @Mapping(source = "id", target = "receiptId")
    ReceiptResponse toResponse(Receipt Receipt);

    void updateReceipt(@MappingTarget Receipt receipt, ReceiptRequest request);
}
