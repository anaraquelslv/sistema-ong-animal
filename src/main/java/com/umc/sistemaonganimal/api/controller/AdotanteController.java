package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.service.AdotanteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adotantes")
public class AdotanteController {

    @Autowired
    AdotanteService adotanteService;

    @GetMapping()
    public List<Adotante> listar(){
        return adotanteService.listar();
    }

    @GetMapping("/{adotanteId}")
    public Adotante buscar(@PathVariable Long adotanteId){
        return adotanteService.buscarPorId(adotanteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Adotante adicionar(@RequestBody Adotante adotante) {
        return adotanteService.salvar(adotante);
    }

    @PutMapping("/{adotanteId}")
    public Adotante atualizar(@PathVariable Long adotanteId, @RequestBody Adotante adotante) {
        Adotante adotanteAtualizar = adotanteService.buscarPorId(adotanteId);
        BeanUtils.copyProperties(adotante, adotanteAtualizar, "id", "documento", "dadosDemograficos", "contato", "endereco");
        return adotanteService.salvar(adotanteAtualizar);
    }

    @DeleteMapping("/{adotanteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long adotanteId) {
        adotanteService.excluir(adotanteId);
    }


}
