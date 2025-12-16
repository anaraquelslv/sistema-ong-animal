package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.AdotanteInUseException;
import com.umc.sistemaonganimal.domain.exception.AdotanteNotFoundException;
import com.umc.sistemaonganimal.domain.exception.EntityInUseException;
import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.repository.AdotanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdotanteService {

    @Autowired
    AdotanteRepository adotanteRepository;

    public List<Adotante> listar(){
        return adotanteRepository.findAll();
    }

    public Adotante buscarPorId(Long id) {
        return adotanteRepository.findById(id).orElseThrow(() -> new AdotanteNotFoundException(id));
    }

    public Adotante salvar(Adotante adotante){
        return adotanteRepository.save(adotante);
    }

    public void excluir(Long id) {
        try {
            Adotante adotante = buscarPorId(id);
            adotanteRepository.delete(adotante);
        } catch (DataIntegrityViolationException e) {
            throw new AdotanteInUseException(id);
        }

    }
}
