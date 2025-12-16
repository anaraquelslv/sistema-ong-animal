package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.exception.DomainException;
import com.umc.sistemaonganimal.domain.exception.EspecieNotFoundException;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.service.RacaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/racas"})
public class RacaController {

    @Autowired
    RacaService racaService;

    @GetMapping()
    private List<Raca> listarTodos() {
        return racaService.listar();
    }

    @GetMapping("/{racaId}")
    private Raca buscarPorId(@PathVariable Long racaId) {
        return racaService.buscarPorId(racaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Raca adicionar(@RequestBody Raca raca) {
        return racaService.salvar(raca);
    }


    @PutMapping("/{racaId}")
    private Raca atualizar(@PathVariable Long racaId, @RequestBody Raca raca) {

        try {
            Raca racaAtualizar = racaService.buscarPorId(racaId);
            BeanUtils.copyProperties(raca, racaAtualizar, "id");
            return racaService.salvar(racaAtualizar);
        } catch (EspecieNotFoundException e) {
            throw new DomainException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{racaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void excluir(@PathVariable Long racaId){
        racaService.excluir(racaId);
    }
}
