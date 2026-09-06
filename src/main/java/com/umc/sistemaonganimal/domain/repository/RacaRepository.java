package com.umc.sistemaonganimal.domain.repository;

import com.umc.sistemaonganimal.domain.model.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long> {

    boolean existsByNomeIgnoreCaseAndEspecieId(String nome, Long especieId);

    boolean existsByNomeIgnoreCaseAndEspecieIdAndIdNot(String nome, Long especieId, Long id);
}
