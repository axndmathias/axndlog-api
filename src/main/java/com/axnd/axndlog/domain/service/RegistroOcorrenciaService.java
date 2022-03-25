package com.axnd.axndlog.domain.service;

import javax.transaction.Transactional;

import com.axnd.axndlog.domain.model.Entrega;
import com.axnd.axndlog.domain.model.Ocorrencia;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
