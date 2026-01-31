package com.github.cobaltmonster.service;

import com.github.cobaltmonster.dto.TransacaoDTO;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class TransacaoService {

    public final List<TransacaoDTO> transacoes = new CopyOnWriteArrayList<>();

    public void add(TransacaoDTO transacao) {
        transacoes.add(transacao);
    }

    public void deleteAll() {
        transacoes.clear();
    }

    public DoubleSummaryStatistics getLastMinuteStats() {
        return transacoes.stream()
                .filter(t -> t.dataHora().isAfter(OffsetDateTime.now().minusSeconds(60)))
                .mapToDouble(t -> t.valor().doubleValue())
                .summaryStatistics();
    }

}

