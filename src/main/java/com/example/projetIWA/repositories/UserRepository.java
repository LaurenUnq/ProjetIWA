package com.example.projetIWA.repositories;

import com.example.projetIWA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * return all contact case for given location informations except given user's id
     * @param latitudeMax - the latitude max
     * @param latitudeMin - the latitude min
     * @param longitudeMax -the longitude max
     * @param longitudeMin - the longitude min
     * @param locationDateMax - the location date max
     * @param locationDateMin - hte location date min
     * @param user_id - the user id to not select
     * @return the list of all users contact case
     */
    @Query(value = "SELECT DISTINCT * FROM users u " +
            "LEFT JOIN user_locations ul ON u.user_id = ul.user_id " +
            "LEFT JOIN locations l ON ul.location_id = l.location_id " +
            "WHERE l.latitude <= ?1 " +
            "AND l.latitude >= ?2 " +
            "AND l.longitude <= ?3 " +
            "AND l.longitude >= ?4 " +
            "AND l.location_date <= ?5 " +
            "AND l.location_date >= ?6 " +
            "AND u.user_id != ?7"
            , nativeQuery = true)
    List<User> findAllContactCase(double latitudeMax,
                                  double latitudeMin,
                                  double longitudeMax,
                                  double longitudeMin,
                                  Date locationDateMax,
                                  Date locationDateMin,
                                  long user_id);
}

