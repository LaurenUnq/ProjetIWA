package com.example.projetIWA.repositories;

import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}

