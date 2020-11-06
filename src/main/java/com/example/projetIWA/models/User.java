package com.example.projetIWA.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handle"})
@Access(AccessType.FIELD)
public class User {

    @Id
    private String username;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private long user_id;

    private String first_name;

    private String last_name;

    private String email;

/*
    @NotNull
    private String username;
*/
    @NotNull
    private String password;

    @ManyToMany
    @JoinTable(name="user_locations",
            joinColumns = @JoinColumn(name="username"),
            inverseJoinColumns = @JoinColumn(name="location_id"))
    private List<Location> locations;

    @ManyToMany
    @JoinTable(name="user_notifications",
            joinColumns = @JoinColumn(name="username"),
            inverseJoinColumns = @JoinColumn(name="notification_id"))
    private List<Location> notifications;

    /* SETTER & GETTER */
/*
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
*/
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
/*
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
*/
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


}
