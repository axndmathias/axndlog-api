package com.axnd.axndlog.api.controller;

import javax.validation.Valid;

import com.axnd.axndlog.domain.repository.EntregaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.axnd.axndlog.domain.model.Entrega;
import com.axnd.axndlog.domain.service.SolicitacaoEntregaService;
import com.axnd.axndlog.api.assembler.EntregaAssembler;
import com.axnd.axndlog.api.model.EntregaModel;
import com.axnd.axndlog.api.model.input.EntregaInput;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntrgeService;
	private EntregaAssembler entregaAssembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntrgeService.solicitar(novaEntrega);

		return entregaAssembler.toModel(entregaSolicitada);
	}

	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}

}
