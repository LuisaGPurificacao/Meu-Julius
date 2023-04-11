package br.com.fiap.meujulius.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.meujulius.models.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    // @Query("""
    //         SELECT d FROM Despesa d 
    //         WHERE d.descricao ilike %:descricao% 
    //         """)
    Page<Despesa> findByDescricaoContaining(String descricao, Pageable pageable);

}
