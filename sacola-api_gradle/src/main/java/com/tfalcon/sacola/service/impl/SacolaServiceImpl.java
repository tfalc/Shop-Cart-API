package com.tfalcon.sacola.service.impl;

import com.tfalcon.sacola.enums.FormaPagamento;
import com.tfalcon.sacola.model.Item;
import com.tfalcon.sacola.model.Restaurante;
import com.tfalcon.sacola.model.Sacola;
import com.tfalcon.sacola.repository.ItemRepository;
import com.tfalcon.sacola.repository.ProdutoRepository;
import com.tfalcon.sacola.repository.SacolaRepository;
import com.tfalcon.sacola.resource.dto.ItemDto;
import com.tfalcon.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item inserirItemSacola(ItemDto itemDto) {

        Sacola sacola = verSacola(itemDto.getIdSacola());

        if (sacola.isFechada()) {
            throw new RuntimeException("Sacola está fechada");
        }

        Item itemParaInserir = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Produto não encontrado");
                        }
                ))
                .build();

        List<Item> itens = sacola.getItens();
        if (itens.isEmpty()) {
            itens.add(itemParaInserir);
        } else {
            Restaurante restaurante = itens.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoProduto = itemParaInserir.getProduto().getRestaurante();

            if (restaurante.equals(restauranteDoProduto)) {
                itens.add(itemParaInserir);
            } else {
                throw new RuntimeException("Não foi possível adicionar itens na sacola. Feche a sacola atual.");
            }
        }

        List<Double> valorItens = new ArrayList<>();
        for (Item itemUnitario : itens) {
            double valorProduto = itemUnitario.getProduto().getValorUnitario() * itemUnitario.getQuantidade();
            valorItens.add(valorProduto);
        }

        sacolaRepository.save(sacola);

        return itemParaInserir;
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
