package br.com.fiap.meujulius.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.meujulius.models.Conta;
import br.com.fiap.meujulius.repository.ContaRepository;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception {
        contaRepository.saveAll(List.of(
            new Conta(1L, "itau", new BigDecimal(100), "money"),
            new Conta(2L, "bradesco", new BigDecimal(50), "coin"),
            new Conta(3L, "carteira", new BigDecimal(2), "coin")
        ));
    }

}