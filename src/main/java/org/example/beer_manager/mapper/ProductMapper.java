package org.example.beer_manager.mapper;
import org.example.beer_manager.dto.request.ProductRequest;
import org.example.beer_manager.dto.response.ProductResponse;
import org.example.beer_manager.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ProductMapper {

    @Mapping(source = "categoryName", target = "category")
    @Mapping(source = "categoryID", target = "category.id")
    Product toEntity(ProductRequest request);

    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "category.id", target = "categoryID")
    ProductResponse toResponse(Product product);
    void updateSupplier(@MappingTarget Product product, ProductRequest request);
}
