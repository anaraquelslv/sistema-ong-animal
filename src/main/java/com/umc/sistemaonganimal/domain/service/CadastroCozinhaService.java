package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }
}
