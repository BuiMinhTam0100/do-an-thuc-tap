package org.example.beer_manager.mapper;
import org.example.beer_manager.dto.request.SupplierRequest;
import org.example.beer_manager.dto.response.SupplierResponse;
import org.example.beer_manager.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupplierMapper {

    Supplier toEntity(SupplierRequest request);
    SupplierResponse toResponse(Supplier supplier);
    void updateSupplier(@MappingTarget Supplier supplier, SupplierRequest request);
}
