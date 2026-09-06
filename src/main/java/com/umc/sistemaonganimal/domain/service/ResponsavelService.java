package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.DomainException;
import com.umc.sistemaonganimal.domain.exception.ResponsavelInUseException;
import com.umc.sistemaonganimal.domain.exception.ResponsavelNotFoundException;
import com.umc.sistemaonganimal.domain.model.Responsavel;
import com.umc.sistemaonganimal.domain.model.embeddables.Documento;
import com.umc.sistemaonganimal.domain.repository.AnimalRepository;
import com.umc.sistemaonganimal.domain.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public List<Responsavel> listar() {
        return responsavelRepository.findAll();
    }

    @SuppressWarnings("null")
    public Responsavel buscarPorId(Long id) {
        return responsavelRepository.findById(id).orElseThrow(() -> new ResponsavelNotFoundException(id));
    }

    public Responsavel salvar(Responsavel responsavel) {
        Documento documento = responsavel.getDocumento();
        String cpf = documento != null ? documento.getCpf() : null;
        String cnpj = responsavel.getCnpj();

        if (cpf != null && !cpf.isBlank() && cnpj != null && !cnpj.isBlank()) {
            throw new DomainException("CPF e CNPJ não podem ser preenchidos ao mesmo tempo.");
        }

        return responsavelRepository.save(responsavel);
    }

    public void excluir(Long id) {
        Responsavel responsavel = buscarPorId(id);

        if (animalRepository.existsByResponsavelId(id)) {
            throw new ResponsavelInUseException(id);
        }

        responsavel.setAtivo(false);
        responsavelRepository.save(responsavel);
    }
}
