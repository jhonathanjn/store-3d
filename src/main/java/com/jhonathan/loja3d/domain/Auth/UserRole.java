package com.jhonathan.loja3d.domain.Auth;

public enum UserRole {
    ADMIN("admin"),
    CLIENT("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
