package br.com.fiap.meujulius.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.meujulius.exceptions.RestNotFoundException;
import br.com.fiap.meujulius.models.Despesa;
import br.com.fiap.meujulius.repository.DespesaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/despesas")
@SecurityRequirement(name = "bearer-key")
public class DespesaController {

    Logger log = LoggerFactory.getLogger(DespesaController.class);

    @Autowired
    DespesaRepository repository;

    @Autowired
    PagedResourcesAssembler<Despesa> assembler;

    @GetMapping
    public PagedModel<EntityModel<Despesa>> index(@RequestParam(required = false) String descricao,
            @ParameterObject @PageableDefault(size = 5, page = 0) Pageable pageable) {
        log.info(descricao);
        Page<Despesa> despesas = (descricao == null) ? repository.findAll(pageable)
                : repository.findByDescricaoContaining(descricao, pageable);

        return assembler.toModel(despesas);
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "despesa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "dados inválidos, a validação falhou")
    })
    public ResponseEntity<Despesa> create(@RequestBody @Valid Despesa despesa) {
        log.info("cadastrando despesa: " + despesa);
        repository.save(despesa);
        return ResponseEntity.created(despesa.toEntityModel().getRequiredLink("self").toUri()).body(despesa);
    }

    @GetMapping("{id}")
    @Operation(summary = "Detalhes da despesa", description = "Retorna os dados de uma despesa passada pelo parâmetro de path id")
    public EntityModel<Despesa> show(@PathVariable Long id) {
        log.info("buscando despesa com id " + id);
        return getDespesa(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando despesa com id " + id);
        getDespesa(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Despesa despesa) {
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
