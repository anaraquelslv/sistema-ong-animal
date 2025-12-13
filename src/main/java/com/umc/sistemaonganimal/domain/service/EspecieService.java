package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.EspecieNotFoundException;
import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecieService {
    @Autowired
    EspecieRepository especieRepository;

    public List<Especie> listar(){
        return especieRepository.findAll();
    }

    public Especie buscarPorId(Long id){
        return especieRepository.findById(id).orElseThrow(() -> new EspecieNotFoundException(id));
    }
}
