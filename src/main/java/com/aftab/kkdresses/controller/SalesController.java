package com.aftab.kkdresses.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aftab.kkdresses.dto.SaleRequest;
import com.aftab.kkdresses.entity.Sale;
import com.aftab.kkdresses.entity.User;
import com.aftab.kkdresses.repository.SaleRepository;
import com.aftab.kkdresses.repository.UserRepository;
import com.aftab.kkdresses.util.PriceDecoder;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.aftab.kkdresses.dto.BulkSaleRequest;
@RestController
public class SalesController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/calculate-profit")
    public Object calculateProfit(
            @RequestBody SaleRequest request
    ) {

        String soldBy =
                request.getSoldBy().toUpperCase();

        // FIND USER

        Optional<User> user =
                userRepository.findByUsername(
                        soldBy
                );

        if (user.isEmpty()) {
            return "Invalid User ❌";
        }

        Long shopId =
                user.get().getShop_id();

        // DECODE PRICE

        Long actualPrice =
                PriceDecoder.decodePrice(
                        request.getSecretCode()
                );

        if (actualPrice == null) {
            return "Invalid Secret Code ❌";
        }

        // CALCULATE PROFIT

        Long profit =
                request.getSoldPrice()
                - actualPrice;

        // PAYMENT LOGIC

        Long finalCash = 0L;
        Long finalOnline = 0L;

        if (request.getPaymentMode()
                .equals("CASH")) {

            finalCash =
                    request.getSoldPrice();

        } else if (request.getPaymentMode()
                .equals("ONLINE")) {

            finalOnline =
                    request.getSoldPrice();

        } else {

            finalCash =
                    request.getCashAmount();

            finalOnline =
                    request.getOnlineAmount();
        }

        // CREATE SALE

        Sale sale = new Sale(
                request.getCategory(),
                request.getSecretCode(),
                actualPrice,
                request.getSoldPrice(),
                profit,
                soldBy,
                request.getPaymentMode(),
                finalCash,
                finalOnline,
                shopId
        );

        // SAVE TO DATABASE

        saleRepository.save(sale);

        return "Sale Added Successfully 🚀";
    }
    @Transactional
    @PostMapping("/calculate-profit/bulk")
    public Object bulkSale(
            @RequestBody BulkSaleRequest request
    ) {

        String soldBy =
                request.getSoldBy().toUpperCase();

        Optional<User> user =
                userRepository.findByUsername(
                        soldBy
                );

        if (user.isEmpty()) {
            return "Invalid User ❌";
        }

        Long shopId =
                user.get().getShop_id();

        List<SaleRequest> items =
                request.getItems();

        for (SaleRequest item : items) {

            Long actualPrice =
                    PriceDecoder.decodePrice(
                            item.getSecretCode()
                    );

            if (actualPrice == null) {

                throw new RuntimeException(
                        "Invalid Secret Code ❌"
                );
            }

            Long profit =
                    item.getSoldPrice()
                    - actualPrice;

            Long finalCash = 0L;
            Long finalOnline = 0L;

            if (item.getPaymentMode()
                    .equals("CASH")) {

                finalCash =
                        item.getSoldPrice();

            } else if (item.getPaymentMode()
                    .equals("ONLINE")) {

                finalOnline =
                        item.getSoldPrice();

            } else {

                finalCash =
                        item.getCashAmount();

                finalOnline =
                        item.getOnlineAmount();
            }

            Sale sale = new Sale(
                    item.getCategory(),
                    item.getSecretCode(),
                    actualPrice,
                    item.getSoldPrice(),
                    profit,
                    soldBy,
                    item.getPaymentMode(),
                    finalCash,
                    finalOnline,
                    shopId
            );

            saleRepository.save(sale);
        }

        return "Bulk Sale Added Successfully 🚀";
    }	
    @GetMapping("/owner/summary")
    public Object getSummary(
            @RequestParam String username
    ) {

        Optional<User> owner =
                userRepository.findByUsernameAndRole(
                        username.toUpperCase(),
                        "OWNER"
                );

        if (owner.isEmpty()) {
            return "Invalid Owner ❌";
        }

        Long shopId =
                owner.get().getShop_id();

        return saleRepository.getSummary(shopId);
    }
    @GetMapping("/owner/category-stats")
    public Object getCategoryStats(
            @RequestParam String username
    ) {

        Optional<User> owner =
                userRepository.findByUsernameAndRole(
                        username.toUpperCase(),
                        "OWNER"
                );

        if (owner.isEmpty()) {
            return "Invalid Owner ❌";
        }

        Long shopId =
                owner.get().getShop_id();

        return saleRepository.getCategoryStats(
                shopId
        );
    }
    @GetMapping("/owner/sales-history")
    public Object getSalesHistory(
            @RequestParam String username
    ) {

        Optional<User> owner =
                userRepository.findByUsernameAndRole(
                        username.toUpperCase(),
                        "OWNER"
                );

        if (owner.isEmpty()) {
            return "Invalid Owner ❌";
        }

        Long shopId =
                owner.get().getShop_id();

        return saleRepository
                .getSalesHistory(shopId);
    }
    @DeleteMapping("/owner/delete-sale/{id}")
    public Object deleteSale(
            @PathVariable Long id,
            @RequestParam String username
    ) {

        Optional<User> owner =
                userRepository.findByUsernameAndRole(
                        username.toUpperCase(),
                        "OWNER"
                );

        if (owner.isEmpty()) {
            return "Invalid Owner ❌";
        }

        Long shopId =
                owner.get().getShop_id();

        Optional<Sale> sale =
        		saleRepository.findSaleByIdAndShop(
        		        id,
        		        shopId
        		);

        if (sale.isEmpty()) {
            return "Sale Not Found ❌";
        }

        saleRepository.deleteById(id);

        return "Sale Deleted Successfully 🚀";
    }
}