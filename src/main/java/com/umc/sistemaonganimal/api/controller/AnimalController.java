package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.service.AnimalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Animal adicionar(@RequestBody Animal animal) {
        return animalService.salvar(animal);
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long animalId, @RequestBody Animal animal) {
        Animal animalAtualizar = animalService.buscarPorId(animalId);

        BeanUtils.copyProperties(animal, animalAtualizar, "id");

        animalAtualizar = animalService.salvar(animalAtualizar);

        return ResponseEntity.ok(animalAtualizar);
    }

//    TODO implementar exclusão lógica dos registros

    @DeleteMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long animalId) {
        animalService.excluir(animalId);
    }
}
