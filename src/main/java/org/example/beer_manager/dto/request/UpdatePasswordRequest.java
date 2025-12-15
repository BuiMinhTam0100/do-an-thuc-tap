package org.example.beer_manager.dto.request;

public record UpdatePasswordRequest(
        String password,
        String confirmPassword
) {
}
