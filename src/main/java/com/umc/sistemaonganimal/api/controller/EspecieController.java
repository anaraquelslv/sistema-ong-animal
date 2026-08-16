package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.api.dto.response.EspecieResponseDTO;
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
    public List<EspecieResponseDTO> listar(){
        return especieService.listar().stream()
                .map(EspecieResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{especieId}")
    public EspecieResponseDTO buscarPorId(@PathVariable Long especieId) {
        return EspecieResponseDTO.fromEntity(especieService.buscarPorId(especieId));
    }
}
