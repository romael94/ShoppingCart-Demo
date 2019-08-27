package com.Romael.ShoppingCart.repository;

import com.Romael.ShoppingCart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(@Param("email") String email);
    User findByUsername(@Param("username") String username);
}
