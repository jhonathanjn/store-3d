package com.jhonathan.loja3d.dto;

import com.jhonathan.loja3d.domain.Auth.UserRole;

public record RegisterDTO(
        String name,
        String email,
        String password,
        UserRole role
) {
}
