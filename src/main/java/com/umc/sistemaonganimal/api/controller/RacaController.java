package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.service.CadastroRacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/racas"})
public class RacaController {

    @Autowired
    CadastroRacaService racaService;

    @GetMapping()
    private List<Raca> listarTodos(){
        return racaService.listarTodos();
    }

}
