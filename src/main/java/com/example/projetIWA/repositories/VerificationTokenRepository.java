package com.example.projetIWA.repositories;

import com.example.projetIWA.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
}
