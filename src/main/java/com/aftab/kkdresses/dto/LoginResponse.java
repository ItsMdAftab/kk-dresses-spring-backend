package com.aftab.kkdresses.dto;

public class LoginResponse {

    private String role;
    private Long shop_id;

    public LoginResponse(String role, Long shop_id) {
        this.role = role;
        this.shop_id = shop_id;
    }

    public String getRole() {
        return role;
    }

    public Long getShop_id() {
        return shop_id;
    }
}