package com.aftab.kkdresses.dto;

public class SummaryResponse {

    private Long sales;
    private Long profit;
    private Long count;
    private Long cash_total;
    private Long online_total;

    public SummaryResponse(
            Long sales,
            Long profit,
            Long count,
            Long cash_total,
            Long online_total
    ) {
        this.sales = sales;
        this.profit = profit;
        this.count = count;
        this.cash_total = cash_total;
        this.online_total = online_total;
    }

    public Long getSales() {
        return sales;
    }

    public Long getProfit() {
        return profit;
    }

    public Long getCount() {
        return count;
    }

    public Long getCash_total() {
        return cash_total;
    }

    public Long getOnline_total() {
        return online_total;
    }
}