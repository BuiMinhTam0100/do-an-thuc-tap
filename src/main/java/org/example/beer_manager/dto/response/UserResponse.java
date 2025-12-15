package org.example.beer_manager.dto.response;

import java.time.Instant;

public record UserResponse(
        Integer id,
        String fullName,
        String username,
        String password,
        Integer roleId,
        String roleName,
        Byte status,
        Instant deletedAt
) {
}
