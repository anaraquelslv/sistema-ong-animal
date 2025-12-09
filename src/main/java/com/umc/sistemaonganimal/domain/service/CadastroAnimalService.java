package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.AnimalNotFoundException;
import com.umc.sistemaonganimal.domain.exception.EntityInUseException;
import com.umc.sistemaonganimal.domain.exception.EntityNotFoundException;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> listarTodos(){
        return animalRepository.findAll();
    }

    public Animal buscarPorId(Long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id) {
                });
    }

    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }

    public void excluir(Long id) {
        try {
            Animal animalExcluir = buscarPorId(id);
            animalRepository.delete(animalExcluir);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("A entidade com id %d está em uso e não pode ser excluida", id));
        }
    }
}
