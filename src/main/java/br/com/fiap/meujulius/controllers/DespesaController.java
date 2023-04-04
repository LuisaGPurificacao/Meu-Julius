package br.com.fiap.meujulius.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.meujulius.exceptions.RestNotFoundException;
import br.com.fiap.meujulius.models.Despesa;
import br.com.fiap.meujulius.repository.DespesaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    Logger log = LoggerFactory.getLogger(DespesaController.class);
    
    List<Despesa> despesas = new  ArrayList<>();

    @Autowired
    DespesaRepository repository;

    @GetMapping
    public List<Despesa> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Despesa> create(@RequestBody @Valid Despesa despesa){
        log.info("cadastrando despesa: " + despesa);
        repository.save(despesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        log.info("buscando despesa com id " + id);
        var despesa = getDespesa(id);
        return ResponseEntity.ok(despesa);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("apagando despesa com id " + id);
        getDespesa(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Despesa despesa){
        log.info("atualizando despesa com id " + id);
        getDespesa(id);
        despesa.setId(id);
        repository.save(despesa);       
        return ResponseEntity.ok(despesa);
    }

    private Despesa getDespesa(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("despesa não encontrada"));
    }

}
