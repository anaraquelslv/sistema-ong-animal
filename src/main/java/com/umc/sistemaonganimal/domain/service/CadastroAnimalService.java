package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.EntidadeNaoEncontradaException;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> listarTodos(){
        return animalRepository.findAll();
    }

    public Animal buscarPorId(Long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Animal não encontrado com o id: " + id));
    }

    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }
}
