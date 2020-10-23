package com.example.projetIWA.repositories;

import com.example.projetIWA.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
