package com.tfalcon.sacola.service.impl;

import com.tfalcon.sacola.enums.FormaPagamento;
import com.tfalcon.sacola.model.Item;
import com.tfalcon.sacola.model.Sacola;
import com.tfalcon.sacola.repository.SacolaRepository;
import com.tfalcon.sacola.resource.dto.ItemDto;
import com.tfalcon.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolaRepository;

    @Override
    public Item inserirItemSacola(ItemDto itemDto) {
        return null;
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id)
                .orElseThrow(
                        () -> {
                            throw new RuntimeException("Essa sacola não existe");
                        }
                );
    }

    @Override
    public Sacola fecharSacola(Long id, int formaPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola");
        }

        FormaPagamento tipoFormaPagamento = formaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
        sacola.setFormaPagamento(tipoFormaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);
    }

}
