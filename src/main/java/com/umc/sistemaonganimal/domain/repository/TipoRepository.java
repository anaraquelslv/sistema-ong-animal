package com.umc.sistemaonganimal.domain.repository;

import com.umc.sistemaonganimal.domain.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
