package com.example.projetIWA.services;

import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServices {

    @Autowired
    private UserRepository userRepository;

    /**
     * check if userId exist in db
     * @param userId - the user id to check
     * @return true if userId exist in db, if not false
     */
    public boolean userExist(String userId) {
        return userRepository.findById(userId).isPresent();
    }

    /**
     * return the user coresponding to the given user id
     * @param userId - the user id
     * @return the user coresponding to the given user id if he exist
     */
    public User findById(String userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> findAll() { return userRepository.findAll();}

    public void save(User user){ userRepository.save(user); }
}
