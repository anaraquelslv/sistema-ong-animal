package com.umc.sistemaonganimal.domain.repository;

import com.umc.sistemaonganimal.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
