package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import com.umc.sistemaonganimal.domain.service.CadastroAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private CadastroAnimalService animalService;

    @GetMapping
    public List<Animal> listar(){
        return animalRepository.findAll();
    }


}
