package com.umc.sistemaonganimal.domain.repository;

import com.umc.sistemaonganimal.domain.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
