package com.github.cobaltmonster.controllers;

import com.github.cobaltmonster.dto.EstatisticaDTO;
import com.github.cobaltmonster.service.TransacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.DoubleSummaryStatistics;

@Path("/estatistica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstatisticaController {

    @Inject
    TransacaoService transacaoService;

    @GET
    public Response getEstatistica() {
        DoubleSummaryStatistics stats = transacaoService.getLastMinuteStats();
        EstatisticaDTO dto = new EstatisticaDTO(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());

        if (stats.getCount() == 0) {
            dto = new EstatisticaDTO(0,0,0,0,0);
        }

        return Response.ok(dto).build();
    }
}
