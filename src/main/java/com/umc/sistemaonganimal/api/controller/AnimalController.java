package com.umc.sistemaonganimal.api.controller;

import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.service.CadastroAnimalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private CadastroAnimalService animalService;

    @GetMapping
    public List<Animal> listar() {
        return animalService.listarTodos();
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable("animalId") Long id) {
        Animal animalEncontrado = animalService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(animalEncontrado);
    }

//    TODO: Altera para erro bad request quando o corpo da requisição não for envidado corretamente (no momento está 500)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal adicionar(@RequestBody Animal animal) {
        return animalService.salvar(animal);
    }

    // TODO Organizar status de reposta HTTP depois que adicionar chave estrangeiras: NOT FOUND: quando o id passado não for encontrado e BAD
    //  REQUEST quando o corpo da requisição conter o id de uma chave estrageira que não existe

    @PutMapping("/{animalId}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long animalId, @RequestBody Animal animal) {
        Animal animalAtualizar = animalService.buscarPorId(animalId);

        BeanUtils.copyProperties(animal, animalAtualizar, "id");

        animalAtualizar = animalService.salvar(animalAtualizar);

        return ResponseEntity.ok(animalAtualizar);
    }

//    TODO implementar exclusão lógica dos registros

    @DeleteMapping("/{animalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluir(@PathVariable Long animalId) {
        animalService.excluir(animalId);
        return ResponseEntity.noContent().build();
    }
}
