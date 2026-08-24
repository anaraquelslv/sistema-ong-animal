package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.AnimalNotFoundException;
import com.umc.sistemaonganimal.domain.exception.DomainException;
import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    @SuppressWarnings("null")
    public Animal buscarPorId(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id) {
                });
    }

    public Animal salvar(Animal animal) {

        Long racaId = animal.getRaca().getId();
        Raca raca = racaService.buscarPorId(racaId);
        animal.setRaca(raca);

        if (animal.getStatus().equals(AnimalStatus.ADOTADO)) {
            if (animal.getAdotante() == null || animal.getAdotante().getId() == null) {
                throw new DomainException(
                        "É obrigatório informar o adotante quando o status do animal é ADOTADO.");
            }
            Long adotanteId = animal.getAdotante().getId();
            Adotante adotante = adotanteService.buscarPorId(adotanteId);
            animal.setAdotante(adotante);
        } else {
            // Garante consistência: animal sem status ADOTADO não mantém adotante vinculado.
            animal.setAdotante(null);
        }

        return animalRepository.save(animal);
    }

    public void excluir(Long id) {
        Animal animalExcluir = buscarPorId(id);
        animalExcluir.setAtivo(false);
        animalRepository.save(animalExcluir);
    }
}
