package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.api.dto.request.AnimalRequestDTO;
import com.umc.sistemaonganimal.api.dto.response.AnimalResponseDTO;
import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<AnimalResponseDTO> listar() {
        return animalService.listar().stream()
                .map(AnimalResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{animalId}")
    public AnimalResponseDTO buscarPorId(@PathVariable("animalId") Long id) {
        return AnimalResponseDTO.fromEntity(animalService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponseDTO adicionar(@RequestBody @Valid AnimalRequestDTO animalDTO) {
        Animal animal = animalService.salvar(animalDTO.toEntity());
        return AnimalResponseDTO.fromEntity(animal);
    }

    @PutMapping("/{animalId}")
    public AnimalResponseDTO atualizar(@PathVariable Long animalId, @RequestBody @Valid AnimalRequestDTO animalDTO) {
        Animal animalAtualizar = animalService.buscarPorId(animalId);

        animalAtualizar.setNome(animalDTO.getNome());
        animalAtualizar.setIdade(animalDTO.getIdade());
        animalAtualizar.setPorte(animalDTO.getPorte());
        animalAtualizar.setSexo(animalDTO.getSexo());
        animalAtualizar.setStatus(animalDTO.getStatus());
        animalAtualizar.setCastrado(animalDTO.isCastrado());
        animalAtualizar.setDataResgate(animalDTO.getDataResgate());
        animalAtualizar.setDataSaida(animalDTO.getDataSaida());
        animalAtualizar.setCorOlhos(animalDTO.getCorOlhos());
        animalAtualizar.setCorPelagem(animalDTO.getCorPelagem());
        animalAtualizar.setObservacao(animalDTO.getObservacao());
        animalAtualizar.setRaca(Raca.builder().id(animalDTO.getRacaId()).build());
        animalAtualizar.setAdotante(animalDTO.getAdotanteId() != null
                ? Adotante.builder().id(animalDTO.getAdotanteId()).build()
                : null);

        Animal animal = animalService.salvar(animalAtualizar);
        return AnimalResponseDTO.fromEntity(animal);
    }

    @DeleteMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long animalId) {
        animalService.excluir(animalId);
    }
}
