package com.Romael.ShoppingCart.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "roleid")
    private int roleid;

    @Column(name = "role")
    private String role;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<User> users;


    //Default Constructor
    public Role() {

    }

    //Constructor
    public Role(int roleid, String role, Collection<User> users) {
        this.roleid = roleid;
        this.role = role;
        this.users = users;
    }


    //Getters and Setters
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
