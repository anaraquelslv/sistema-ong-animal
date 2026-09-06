package com.umc.sistemaonganimal.domain.repository;

import com.umc.sistemaonganimal.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    boolean existsByRacaId(Long racaId);

    boolean existsByAdotanteId(Long adotanteId);

    boolean existsByResponsavelId(Long responsavelId);

    List<Animal> findByResponsavelId(Long responsavelId);
}
