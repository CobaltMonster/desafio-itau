package com.github.cobaltmonster.controllers;

import com.github.cobaltmonster.dto.TransacaoDTO;
import com.github.cobaltmonster.service.TransacaoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Path("/transacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransacaoController {

    @Inject
    TransacaoService transacaoService;

    @POST
    @Transactional
    public Response adicionarTransacao(TransacaoDTO dto) {
        if (dto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (dto.valor().compareTo(BigDecimal.ZERO) < 0) {
            return Response.status(422).build();
        }

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            return Response.status(422).build();
        }

        transacaoService.add(dto);

        return Response.status(201).build();
    }
}
