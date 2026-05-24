package com.aftab.kkdresses.dto;

public class CategoryStatsResponse {

    private String category;

    private Long count;

    private Long sales;

    private Long profit;

    public CategoryStatsResponse(
            String category,
            Long count,
            Long sales,
            Long profit
    ) {
        this.category = category;
        this.count = count;
        this.sales = sales;
        this.profit = profit;
    }

    public String getCategory() {
        return category;
    }

    public Long getCount() {
        return count;
    }

    public Long getSales() {
        return sales;
    }

    public Long getProfit() {
        return profit;
    }
}