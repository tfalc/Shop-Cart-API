package com.tfalcon.sacola.service;

import com.tfalcon.sacola.model.Item;
import com.tfalcon.sacola.model.Sacola;
import com.tfalcon.sacola.resource.dto.ItemDto;

public interface SacolaService {

    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);
    Item inserirItemSacola(ItemDto itemDto);
}
