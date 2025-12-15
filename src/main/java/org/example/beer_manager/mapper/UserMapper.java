package org.example.beer_manager.mapper;
import org.example.beer_manager.dto.request.CreateUserRequest;
import org.example.beer_manager.dto.request.UpdateUserRequest;
import org.example.beer_manager.dto.response.UserResponse;
import org.example.beer_manager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    User createToEntity(CreateUserRequest request);


    @Mapping(source = "role.roleName", target = "roleName")
    UserResponse toResponse(User user);

    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
