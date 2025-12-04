package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.service.CadastroAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private CadastroAnimalService animalService;

    @GetMapping
    public List<Animal> listar(){
        return animalService.listarTodos();
    }

    @GetMapping("/{animalId}")
    public Animal buscarPorId(@PathVariable("animalId") Long id){
        return animalService.buscarPorId(id);
    }


}
