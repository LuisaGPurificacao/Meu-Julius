package br.com.fiap.meujulius.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.meujulius.models.Despesa;

@RestController
public class DespesaController {
    
    @GetMapping("/api/despesas")
    public Despesa show(){
        return new Despesa(new BigDecimal(100), LocalDate.now(), "Cinema");
    }

}
