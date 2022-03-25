package com.axnd.axndlog.domain.service;

import com.axnd.axndlog.domain.exception.EntidadeNaoEncontradaException;
import com.axnd.axndlog.domain.model.Entrega;
import com.axnd.axndlog.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
