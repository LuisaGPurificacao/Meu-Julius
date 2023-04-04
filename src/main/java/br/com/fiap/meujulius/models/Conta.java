package br.com.fiap.meujulius.models;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Conta {
    
    private Long id;
    private String nome;
    private BigDecimal saldoInicial;
    private String icone;

}
