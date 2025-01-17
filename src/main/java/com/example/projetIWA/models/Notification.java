package com.example.projetIWA.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="notifications")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handle"})
@Access(AccessType.FIELD)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notification_id;

    @NotNull
    private String description;

    @NotNull
    private Boolean viewed;

    @NotNull
    private Date notification_date;

    @ManyToMany(mappedBy = "notifications")
    @JsonIgnore // Pour ne pas produire des cycles
    private List<User> users;

    public long getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(long user_id) {
        this.notification_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public Date getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(Date notification_date) {
        this.notification_date = notification_date;
    }
}
