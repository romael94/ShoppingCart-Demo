package com.Romael.ShoppingCart.repository;

import com.Romael.ShoppingCart.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(@Param("role") String role);
}
