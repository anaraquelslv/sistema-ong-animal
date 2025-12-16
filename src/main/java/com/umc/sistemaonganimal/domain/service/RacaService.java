package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.EntityInUseException;
import com.umc.sistemaonganimal.domain.exception.EspecieNotFoundException;
import com.umc.sistemaonganimal.domain.exception.RacaInUseException;
import com.umc.sistemaonganimal.domain.exception.RacaNotFoundException;
import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.repository.EspecieRepository;
import com.umc.sistemaonganimal.domain.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacaService {

    @Autowired
    RacaRepository racaRepository;

    @Autowired
    EspecieService especieService;

    public List<Raca> listar(){
        return racaRepository.findAll();
    }

    public Raca buscarPorId(Long id){
        return racaRepository.findById(id).orElseThrow(() -> new RacaNotFoundException(id));
    }

    public Raca salvar(Raca raca) {

        Long especieId = raca.getEspecie().getId();
        Especie especie = especieService.buscarPorId(especieId);

        raca.setEspecie(especie);

        return racaRepository.save(raca);
    }

    public void excluir(Long id) {
        try {
            Raca raca = buscarPorId(id);
            racaRepository.delete(raca);
        } catch (DataIntegrityViolationException e) {
            throw new RacaInUseException(id);
        }

    }

}
