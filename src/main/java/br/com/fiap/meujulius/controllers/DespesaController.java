package br.com.fiap.meujulius.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.meujulius.models.Despesa;

@RestController
public class DespesaController {

    Logger log = LoggerFactory.getLogger(DespesaController.class);
    
    List<Despesa> despesas = new  ArrayList<>();

    @GetMapping("/api/despesas")
    public List<Despesa> index(){
        return despesas;
    }

    @PostMapping("/api/despesas")
    public ResponseEntity<Despesa> create(@RequestBody Despesa despesa){
        log.info("cadastrando despesa: " + despesa);
        despesa.setId(despesas.size() + 1l);
        despesas.add(despesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping("/api/despesas/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        log.info("buscando despesa com id " + id);
        Optional<Despesa> optionalDespesa = despesas.stream().filter(d -> d.getId().equals(id)).findFirst();
        if (optionalDespesa.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Despesa não encontrada");
        return ResponseEntity.ok(optionalDespesa.get());
    }

}
