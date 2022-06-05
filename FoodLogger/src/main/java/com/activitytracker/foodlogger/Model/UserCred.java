package com.activitytracker.foodlogger.Model;

import javax.persistence.*;

@Entity
public class UserCred {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String username;
    private String password;

    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private User userDetails;

    public UserCred(String username, String password, String role, User userDetails) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userDetails = userDetails;
    }

    public UserCred(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserCred() {
    }

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
