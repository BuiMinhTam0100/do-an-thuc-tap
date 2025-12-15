package org.example.beer_manager.dto.request;

public record UpdateUserRequest(
        String fullName,
        String username,
        Integer roleId,
        String roleName
) {
}
