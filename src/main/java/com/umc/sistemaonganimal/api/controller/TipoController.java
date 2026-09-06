package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.api.dto.response.TipoResponseDTO;
import com.umc.sistemaonganimal.domain.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/tipos"})
public class TipoController {

    @Autowired
    TipoService tipoService;

    @GetMapping
    public List<TipoResponseDTO> listar(){
        return tipoService.listar().stream()
                .map(TipoResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{tipoId}")
    public TipoResponseDTO buscarPorId(@PathVariable Long tipoId) {
        return TipoResponseDTO.fromEntity(tipoService.buscarPorId(tipoId));
    }
}
