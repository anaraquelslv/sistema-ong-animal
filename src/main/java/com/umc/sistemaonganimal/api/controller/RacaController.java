package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.api.dto.request.RacaRequestDTO;
import com.umc.sistemaonganimal.api.dto.response.RacaResponseDTO;
import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.service.RacaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({ "/racas" })
public class RacaController {

    @Autowired
    RacaService racaService;

    @GetMapping()
    private List<RacaResponseDTO> listarTodos() {
        return racaService.listar().stream()
                .map(RacaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{racaId}")
    private RacaResponseDTO buscarPorId(@PathVariable Long racaId) {
        return RacaResponseDTO.fromEntity(racaService.buscarPorId(racaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private RacaResponseDTO adicionar(@RequestBody @Valid RacaRequestDTO racaDTO) {
        Raca raca = racaService.salvar(racaDTO.toEntity());
        return RacaResponseDTO.fromEntity(raca);
    }

    @PutMapping("/{racaId}")
    private RacaResponseDTO atualizar(@PathVariable Long racaId, @RequestBody @Valid RacaRequestDTO racaDTO) {
        Raca racaAtualizar = racaService.buscarPorId(racaId);
        racaAtualizar.setNome(racaDTO.getNome());
        racaAtualizar.setEspecie(Especie.builder().id(racaDTO.getEspecieId()).build());
        Raca raca = racaService.salvar(racaAtualizar);
        return RacaResponseDTO.fromEntity(raca);
    }

    @DeleteMapping("/{racaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void excluir(@PathVariable Long racaId) {
        racaService.excluir(racaId);
    }
}
