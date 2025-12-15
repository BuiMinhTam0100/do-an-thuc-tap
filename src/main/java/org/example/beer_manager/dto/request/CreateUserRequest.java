package org.example.beer_manager.dto.request;

public record CreateUserRequest(
        String fullName,
        String username,
        String password,
        Integer roleId,
        String roleName
) {
}
