package com.jhonathan.loja3d.domain;

public record RegisterDTO(
        String name,
        String email,
        String password,
        UserRole role
) {
}
