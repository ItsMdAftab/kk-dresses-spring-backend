package com.aftab.kkdresses.dto;

public class SaleRequest {

    private String category;
    private String secretCode;
    private Long soldPrice;
    private String paymentMode;
    private Long cashAmount;
    private Long onlineAmount;
    private String soldBy;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public Long getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(Long soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Long getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Long cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Long getOnlineAmount() {
        return onlineAmount;
    }

    public void setOnlineAmount(Long onlineAmount) {
        this.onlineAmount = onlineAmount;
    }

    public String getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(String soldBy) {
        this.soldBy = soldBy;
    }
}