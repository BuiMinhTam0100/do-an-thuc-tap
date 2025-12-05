package org.example.beer_manager.mapper;
import org.example.beer_manager.dto.request.CategoriesRequest;
import org.example.beer_manager.dto.response.CategoriesResponse;
import org.example.beer_manager.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface CategoriesMapper {
    Category toEntity(CategoriesRequest request);
    CategoriesResponse toResponse(Category category);
    void updateSupplier(@MappingTarget Category category, CategoriesRequest request);
}
