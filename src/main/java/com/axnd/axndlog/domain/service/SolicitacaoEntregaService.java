package com.axnd.axndlog.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.axnd.axndlog.domain.model.Cliente;
import com.axnd.axndlog.domain.model.Entrega;
import com.axnd.axndlog.domain.model.StatusEntrega;
import com.axnd.axndlog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private CatalogoClienteService catalagoClienteService;
	private EntregaRepository entregaRepository;

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalagoClienteService.buscar(entrega.getCliente().getId());

		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return entregaRepository.save(entrega);
	}
}
