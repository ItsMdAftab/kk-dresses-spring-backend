package com.aftab.kkdresses.dto;

public class WorkerStatsResponse {

    private String sold_by;

    private Long count;

    private Long profit;

    public WorkerStatsResponse(
            String sold_by,
            Long count,
            Long profit
    ) {
        this.sold_by = sold_by;
        this.count = count;
        this.profit = profit;
    }

    public String getSold_by() {
        return sold_by;
    }

    public Long getCount() {
        return count;
    }

    public Long getProfit() {
        return profit;
    }
}