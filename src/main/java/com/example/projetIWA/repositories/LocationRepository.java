package com.example.projetIWA.repositories;

import com.example.projetIWA.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
