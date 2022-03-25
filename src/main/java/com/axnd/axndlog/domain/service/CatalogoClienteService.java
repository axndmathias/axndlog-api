package com.axnd.axndlog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.axnd.axndlog.domain.exception.NegocioException;
import com.axnd.axndlog.domain.model.Cliente;
import com.axnd.axndlog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;

	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente n�o encontrado"));
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteJaExiste -> !clienteJaExiste.equals(cliente));

		if (emailEmUso) {
			throw new NegocioException("::::: Já existe um cliente cadastrado com este e-mail");
		}

		return clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
