package com.aftab.kkdresses.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @Column(name = "secret_code")
    private String secret_code;

    @Column(name = "actual_price")
    private Long actual_price;

    @Column(name = "sold_price")
    private Long sold_price;

    private Long profit;

    @Column(name = "sold_by")
    private String sold_by;

    @Column(name = "payment_mode")
    private String payment_mode;

    @Column(name = "cash_amount")
    private Long cash_amount;

    @Column(name = "online_amount")
    private Long online_amount;

    @Column(name = "shop_id")
    private Long shop_id;

    public Sale() {

    }

    public Sale(
            String category,
            String secret_code,
            Long actual_price,
            Long sold_price,
            Long profit,
            String sold_by,
            String payment_mode,
            Long cash_amount,
            Long online_amount,
            Long shop_id
    ) {
        this.category = category;
        this.secret_code = secret_code;
        this.actual_price = actual_price;
        this.sold_price = sold_price;
        this.profit = profit;
        this.sold_by = sold_by;
        this.payment_mode = payment_mode;
        this.cash_amount = cash_amount;
        this.online_amount = online_amount;
        this.shop_id = shop_id;
    }

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSecret_code() {
        return secret_code;
    }

    public void setSecret_code(String secret_code) {
        this.secret_code = secret_code;
    }

    public Long getActual_price() {
        return actual_price;
    }

    public void setActual_price(Long actual_price) {
        this.actual_price = actual_price;
    }

    public Long getSold_price() {
        return sold_price;
    }

    public void setSold_price(Long sold_price) {
        this.sold_price = sold_price;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public String getSold_by() {
        return sold_by;
    }

    public void setSold_by(String sold_by) {
        this.sold_by = sold_by;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public Long getCash_amount() {
        return cash_amount;
    }

    public void setCash_amount(Long cash_amount) {
        this.cash_amount = cash_amount;
    }

    public Long getOnline_amount() {
        return online_amount;
    }

    public void setOnline_amount(Long online_amount) {
        this.online_amount = online_amount;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }
}