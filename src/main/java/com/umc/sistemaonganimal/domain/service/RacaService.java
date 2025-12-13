package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacaService {

    @Autowired
    RacaRepository racaRepository;

    public List<Raca> listar(){
        return racaRepository.findAll();
    }

}
