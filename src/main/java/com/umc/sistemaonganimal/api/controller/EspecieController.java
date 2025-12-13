package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/especies"})
public class EspecieController {

    @Autowired
    EspecieService especieService;

    @GetMapping
    public List<Especie> listar(){
        return especieService.listar();
    }
}
