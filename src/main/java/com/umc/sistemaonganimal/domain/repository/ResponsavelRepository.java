package com.umc.sistemaonganimal.domain.repository;

import com.umc.sistemaonganimal.domain.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
}
