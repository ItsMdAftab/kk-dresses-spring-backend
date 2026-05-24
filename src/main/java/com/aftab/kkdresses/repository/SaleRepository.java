package com.aftab.kkdresses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aftab.kkdresses.dto.SummaryResponse;
import com.aftab.kkdresses.entity.Sale;
import com.aftab.kkdresses.dto.*;
import java.util.List;
import java.util.Optional;
public interface SaleRepository
        extends JpaRepository<Sale, Long> {

    @Query("""
    SELECT new com.aftab.kkdresses.dto.SummaryResponse(
    COALESCE(SUM(s.sold_price),0),
    COALESCE(SUM(s.profit),0),
    COUNT(s),
    COALESCE(SUM(s.cash_amount),0),
    COALESCE(SUM(s.online_amount),0)
    )
    FROM Sale s
    WHERE s.shop_id = :shopId
    """)
    SummaryResponse getSummary(Long shopId);
    
    @Query("""
    		SELECT new com.aftab.kkdresses.dto.CategoryStatsResponse(
    		s.category,
    		COUNT(s),
    		COALESCE(SUM(s.sold_price),0),
    		COALESCE(SUM(s.profit),0)
    		)
    		FROM Sale s
    		WHERE s.shop_id = :shopId
    		GROUP BY s.category
    		ORDER BY SUM(s.sold_price) DESC
    		""")
    		List<CategoryStatsResponse>
    		getCategoryStats(Long shopId);
    @Query("""
    		SELECT new com.aftab.kkdresses.dto.WorkerStatsResponse(
    		s.sold_by,
    		COUNT(s),
    		COALESCE(SUM(s.profit),0)
    		)
    		FROM Sale s
    		WHERE s.shop_id = :shopId
    		GROUP BY s.sold_by
    		ORDER BY COUNT(s) DESC
    		""")
    		List<WorkerStatsResponse>
    		getWorkerStats(Long shopId);
    @Query("""
    		SELECT s
    		FROM Sale s
    		WHERE s.shop_id = :shopId
    		ORDER BY s.id DESC
    		""")
    		List<Sale> getSalesHistory(Long shopId);
    @Query("""
    		SELECT s
    		FROM Sale s
    		WHERE s.id = :id
    		AND s.shop_id = :shopId
    		""")
    		Optional<Sale> findSaleByIdAndShop(
    		        Long id,
    		        Long shopId
    		);
}