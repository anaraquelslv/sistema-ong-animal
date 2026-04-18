package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.exception.AdotanteNotFoundException;
import com.umc.sistemaonganimal.domain.exception.DomainException;
import com.umc.sistemaonganimal.domain.exception.RacaNotFoundException;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> listar() {
        return animalService.listar();
    }

    @GetMapping("/{animalId}")
    public Animal buscarPorId(@PathVariable("animalId") Long id) {
        return animalService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal adicionar(@RequestBody @Valid Animal animal) {
        try {
            return animalService.salvar(animal);
        } catch (AdotanteNotFoundException | RacaNotFoundException ex) {
            throw new DomainException(ex.getMessage(), ex);
        }

    }

    @SuppressWarnings("null")
    @PutMapping("/{animalId}")
    public Animal atualizar(@PathVariable Long animalId, @RequestBody @Valid @NonNull Animal animal) {
        try {
            Animal animalAtualizar = animalService.buscarPorId(animalId);

            BeanUtils.copyProperties(animal, animalAtualizar, "id");

            return animalService.salvar(animalAtualizar);

        } catch (AdotanteNotFoundException | RacaNotFoundException ex) {
            throw new DomainException(ex.getMessage(), ex);
        }

    }

//    TODO implementar exclusão lógica dos registros

    @DeleteMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long animalId) {
        animalService.excluir(animalId);
    }
}
