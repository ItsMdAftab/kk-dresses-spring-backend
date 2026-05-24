package com.aftab.kkdresses.dto;

import java.util.List;

public class BulkSaleRequest {

    private String soldBy;

    private List<SaleRequest> items;

    public String getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(String soldBy) {
        this.soldBy = soldBy;
    }

    public List<SaleRequest> getItems() {
        return items;
    }

    public void setItems(List<SaleRequest> items) {
        this.items = items;
    }
}