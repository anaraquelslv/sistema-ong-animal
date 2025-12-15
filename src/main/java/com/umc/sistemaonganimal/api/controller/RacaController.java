package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.service.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/racas"})
public class RacaController {

    @Autowired
    RacaService racaService;

    @GetMapping()
    private List<Raca> listarTodos(){
        return racaService.listar();
    }

    @GetMapping("/{racaId}")
    private Raca buscarPorId(@PathVariable Long racaId){
        return racaService.buscarPorId(racaId);
    }

    @PostMapping
    private Raca adicionar(@RequestBody Raca raca) {
        return racaService.salvar(raca);
    }

}
