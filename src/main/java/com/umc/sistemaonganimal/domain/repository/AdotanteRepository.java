package com.umc.sistemaonganimal.domain.repository;

import com.umc.sistemaonganimal.domain.model.Adotante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdotanteRepository extends JpaRepository<Adotante, Long> {
}
