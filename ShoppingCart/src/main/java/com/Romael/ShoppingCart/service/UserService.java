package com.Romael.ShoppingCart.service;

import com.Romael.ShoppingCart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserService {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User saveUser(User user);
}
