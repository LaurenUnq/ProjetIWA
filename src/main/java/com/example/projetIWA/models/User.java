package com.example.projetIWA.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity(name="user_entity")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handle"})
@Access(AccessType.FIELD)
public class User {

    @Id
    @NotNull
    private String id;

    @Column(unique=true)
    private String username;

    private String first_name;

    private String last_name;

    private String email;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_locations",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="location_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Location> locations;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_notifications",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="notification_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Notification> notifications;


    /* SETTER & GETTER */

    public String getUser_id() {
        return id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setUser_id(String user_id) {
        this.id = user_id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
