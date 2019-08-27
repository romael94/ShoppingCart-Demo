package com.Romael.ShoppingCart.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userid;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "active")
    private Integer active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
    private Collection<Role> roles;

    //Default Constructor
    public User() {

    }

    //Constructor
    public User(Integer userid, @NotNull String firstname, @NotNull String lastname, @NotNull String username, @NotNull String email, @NotNull String password, @NotNull Integer active, Collection<Role> roles) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }


    //Getters and Setters
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
