package com.github.cobaltmonster.service;

import com.github.cobaltmonster.dto.TransacaoDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TransacaoService {

    public final List<TransacaoDTO> transacoes = new ArrayList<>();

    public void add(TransacaoDTO transacao) {
        transacoes.add(transacao);
    }

    public void deleteAll() {
        transacoes.clear();
    }
}

