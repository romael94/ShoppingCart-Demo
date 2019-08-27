package com.Romael.ShoppingCart.service;

import com.Romael.ShoppingCart.model.Role;
import com.Romael.ShoppingCart.model.User;
import com.Romael.ShoppingCart.repository.RoleRepository;
import com.Romael.ShoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user){
        //encode password
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setActive(1);

       //set role to role_user
        user.setRoles(Collections.singletonList(roleRepository.findByRole("ROLE_USER")));
        return userRepository.saveAndFlush(user);

    }

}
