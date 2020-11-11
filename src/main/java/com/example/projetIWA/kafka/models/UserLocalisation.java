package com.example.projetIWA.kafka.models;

import com.example.projetIWA.models.Location;

public class UserLocalisation {

    private Long user_id;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserLocalisation [userName=" + user_id + ", location="
                + location + "]";
    }
}
