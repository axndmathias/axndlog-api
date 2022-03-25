package com.axnd.axndlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.axnd.axndlog.api.assembler.OcorrenciaAssembler;
import com.axnd.axndlog.api.model.OcorrenciaModel;
import com.axnd.axndlog.api.model.input.OcorrenciaInput;
import com.axnd.axndlog.domain.model.Entrega;
import com.axnd.axndlog.domain.model.Ocorrencia;
import com.axnd.axndlog.domain.service.BuscaEntregaService;
import com.axnd.axndlog.domain.service.RegistroOcorrenciaService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;
    private BuscaEntregaService buscaEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
            @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {

        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
                .registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }

}
