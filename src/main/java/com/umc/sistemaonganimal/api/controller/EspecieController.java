package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{especieId}")
    public Especie buscarPorId(@PathVariable Long especieId) {
        return especieService.buscarPorId(especieId);
    }
}
