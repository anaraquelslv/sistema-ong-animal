package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.EntidadeEmUsoException;
import com.umc.sistemaonganimal.domain.exception.EntidadeNaoEncontradaException;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um registro de Animal com o id: %d", id)));
    }

    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }

    public void excluir(Long id) {
        try {
            Animal animalExcluir = buscarPorId(id);
            animalRepository.delete(animalExcluir);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("A entidade com id %d está em uso e não pode ser excluida", id));
        }
    }
}
