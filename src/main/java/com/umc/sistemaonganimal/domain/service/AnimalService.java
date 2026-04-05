package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.AnimalInUseException;
import com.umc.sistemaonganimal.domain.exception.AnimalNotFoundException;
import com.umc.sistemaonganimal.domain.exception.EntityInUseException;
import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import com.umc.sistemaonganimal.domain.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AdotanteService adotanteService;

    @Autowired
    private RacaService racaService;


    public List<Animal> listar(){
        return animalRepository.findAll();
    }

    public Animal buscarPorId(Long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id) {
                });
    }

    public Animal salvar(Animal animal) {

        Long racaId = animal.getRaca().getId();
        Raca raca = racaService.buscarPorId(racaId);
        animal.setRaca(raca);

        if (animal.getStatus().equals(AnimalStatus.ADOTADO)) {
            Long adotanteId = animal.getAdotante().getId();
            Adotante adotante = adotanteService.buscarPorId(adotanteId);
            animal.setAdotante(adotante);
        }

        return animalRepository.save(animal);
    }

    public void excluir(Long id) {
        try {
            Animal animalExcluir = buscarPorId(id);
            animalRepository.delete(animalExcluir);
        } catch (DataIntegrityViolationException e) {
            throw new AnimalInUseException(id);
        }
    }
}
