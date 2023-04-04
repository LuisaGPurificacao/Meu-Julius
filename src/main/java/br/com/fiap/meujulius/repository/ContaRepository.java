package br.com.fiap.meujulius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.meujulius.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
