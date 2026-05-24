package com.aftab.kkdresses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftab.kkdresses.entity.Shop;

public interface ShopRepository
        extends JpaRepository<Shop, Long> {

    Optional<Shop> findByName(String name);
}