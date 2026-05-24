package com.aftab.kkdresses.dto;

public class LoginJwtResponse {

    private String token;

    private String role;

    private Long shop_id;

    public LoginJwtResponse(
            String token,
            String role,
            Long shop_id
    ) {
        this.token = token;
        this.role = role;
        this.shop_id = shop_id;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public Long getShop_id() {
        return shop_id;
    }
}