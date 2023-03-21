package br.com.fiap.meujulius.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.meujulius.models.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}