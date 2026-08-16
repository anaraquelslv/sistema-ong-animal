package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.api.dto.request.AdotanteRequestDTO;
import com.umc.sistemaonganimal.api.dto.response.AdotanteResponseDTO;
import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.service.AdotanteService;
import jakarta.validation.Valid;
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
    public List<AdotanteResponseDTO> listar(){
        return adotanteService.listar().stream()
                .map(AdotanteResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{adotanteId}")
    public AdotanteResponseDTO buscar(@PathVariable Long adotanteId){
        return AdotanteResponseDTO.fromEntity(adotanteService.buscarPorId(adotanteId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdotanteResponseDTO adicionar(@RequestBody @Valid AdotanteRequestDTO adotanteDTO) {
        Adotante adotante = adotanteService.salvar(adotanteDTO.toEntity());
        return AdotanteResponseDTO.fromEntity(adotante);
    }

    @PutMapping("/{adotanteId}")
    public AdotanteResponseDTO atualizar(@PathVariable Long adotanteId, @RequestBody @Valid AdotanteRequestDTO adotanteDTO) {
        Adotante adotanteAtualizar = adotanteService.buscarPorId(adotanteId);
        adotanteAtualizar.setNome(adotanteDTO.getNome());
        adotanteAtualizar.setDataNascimento(adotanteDTO.getDataNascimento());
        adotanteAtualizar.setDocumento(adotanteDTO.getDocumento() != null ? adotanteDTO.getDocumento().toEntity() : null);
        adotanteAtualizar.setDadosDemograficos(adotanteDTO.getDadosDemograficos() != null ? adotanteDTO.getDadosDemograficos().toEntity() : null);
        adotanteAtualizar.setContato(adotanteDTO.getContato() != null ? adotanteDTO.getContato().toEntity() : null);
        adotanteAtualizar.setEndereco(adotanteDTO.getEndereco() != null ? adotanteDTO.getEndereco().toEntity() : null);
        Adotante adotante = adotanteService.salvar(adotanteAtualizar);
        return AdotanteResponseDTO.fromEntity(adotante);
    }

    @DeleteMapping("/{adotanteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long adotanteId) {
        adotanteService.excluir(adotanteId);
    }


}
