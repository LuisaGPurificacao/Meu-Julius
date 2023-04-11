package br.com.fiap.meujulius.config;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.meujulius.models.Conta;
import br.com.fiap.meujulius.models.Despesa;
import br.com.fiap.meujulius.repository.ContaRepository;
import br.com.fiap.meujulius.repository.DespesaRepository;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    DespesaRepository despesaRepository;

    @Override
    public void run(String... args) throws Exception {
        Conta c1 = new Conta(1L, "itau", new BigDecimal(100), "money");
        Conta c2 = new Conta(2L, "bradesco", new BigDecimal(50), "coin");
        Conta c3 = new Conta(3L, "carteira", new BigDecimal(2), "coin");
        contaRepository.saveAll(List.of(
                c1, c2, c3));
        despesaRepository.saveAll(List.of(
                Despesa.builder()
                        .descricao("cinema")
                        .valor(new BigDecimal(100))
                        .data(LocalDate.now())
                        .conta(c2)
                        .build(),
                Despesa.builder()
                        .descricao("mercado")
                        .valor(new BigDecimal(450))
                        .data(LocalDate.of(2023, 03, 30))
                        .conta(c2)
                        .build(),
                Despesa.builder()
                        .descricao("aluguel")
                        .valor(new BigDecimal(125))
                        .data(LocalDate.now())
                        .conta(c1)
                        .build(),
                Despesa.builder()
                        .descricao("estacionamento")
                        .valor(new BigDecimal(25.99))
                        .data(LocalDate.now())
                        .conta(c3)
                        .build(),
                Despesa.builder()
                        .descricao("restaurante")
                        .valor(new BigDecimal(289.23))
                        .data(LocalDate.now())
                        .conta(c3)
                        .build(),
                Despesa.builder()
                        .descricao("imposto")
                        .valor(new BigDecimal(225.11))
                        .data(LocalDate.now())
                        .conta(c2)
                        .build(),
                Despesa.builder()
                        .descricao("maquiagem")
                        .valor(new BigDecimal(1025.99))
                        .data(LocalDate.now())
                        .conta(c1)
                        .build(),
                Despesa.builder()
                        .descricao("maquiagem")
                        .valor(new BigDecimal(55))
                        .data(LocalDate.now())
                        .conta(c3)
                        .build()));
    }

}