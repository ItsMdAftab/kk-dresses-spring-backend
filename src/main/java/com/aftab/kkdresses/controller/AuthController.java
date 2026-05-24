package com.aftab.kkdresses.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aftab.kkdresses.security.JwtUtil;
import com.aftab.kkdresses.dto.LoginJwtResponse;
import com.aftab.kkdresses.dto.LoginRequest;
import com.aftab.kkdresses.entity.User;
import com.aftab.kkdresses.repository.*;
import com.aftab.kkdresses.dto.*; 
import com.aftab.kkdresses.entity.*;

import jakarta.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginRequest request) {

        Optional<User> user = userRepository
                .findByUsernameAndPasswordAndRoleIsNotNull(
                        request.getUsername().toUpperCase(),
                        request.getPassword()
                );

        if (user.isPresent()) {

            User loggedUser = user.get();

            String token =
                    JwtUtil.generateToken(
                            loggedUser.getUsername()
                    );

            return new LoginJwtResponse(
                    token,
                    loggedUser.getRole(),
                    loggedUser.getShop_id()
            );
        }

        return "Invalid Credentials ❌";
    }
    @PostMapping("/register-worker")
    public Object registerWorker(
            @RequestBody RegisterWorkerRequest request
    ) {

        String username =
                request.getUsername().toUpperCase();

        String password =
                request.getPassword();

        String ownerUsername =
                request.getOwnerUsername().toUpperCase();

        // CHECK OWNER

        Optional<User> owner =
                userRepository.findByUsernameAndRole(
                        ownerUsername,
                        "OWNER"
                );

        if (owner.isEmpty()) {
            return "Invalid Owner ❌";
        }

        // CHECK EXISTING USER

        Optional<User> existingUser =
                userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            return "Username Already Exists ❌";
        }

        // GET SHOP ID FROM OWNER

        Long shopId =
                owner.get().getShop_id();

        // CREATE WORKER

        User worker = new User(
                username,
                password,
                "WORKER",
                shopId
        );

        // SAVE TO DATABASE

        userRepository.save(worker);

        return "Worker Registered Successfully 🚀";
    }
    @PostMapping("/register-owner")
    public Object registerOwner(
            @RequestBody RegisterOwnerRequest request
    ) {

        String username =
                request.getUsername().toUpperCase();

        String password =
                request.getPassword();

        String shopName =
                request.getShopName();

        // CHECK USER EXISTS

        Optional<User> existingUser =
                userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            return "Username Already Exists ❌";
        }

        // CHECK SHOP EXISTS

        Optional<Shop> existingShop =
                shopRepository.findByName(shopName);

        if (existingShop.isPresent()) {
            return "Shop Already Exists ❌";
        }

        // CREATE SHOP

        Shop shop = new Shop(shopName);

        Shop savedShop =
                shopRepository.save(shop);

        // CREATE OWNER

        User owner = new User(
                username,
                password,
                "OWNER",
                savedShop.getId()
        );

        userRepository.save(owner);

        return "Owner Registered Successfully 🚀";
    }	
}