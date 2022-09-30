package com.tfalcon.sacola.resource;

import com.tfalcon.sacola.model.Item;
import com.tfalcon.sacola.model.Sacola;
import com.tfalcon.sacola.resource.dto.ItemDto;
import com.tfalcon.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sacola")
@RequiredArgsConstructor
public class SacolaController {

    private final SacolaService sacolaService;

    @RequestMapping(value = "/restest")
    public String healthCheck(){
        return "ok";
    }

    @GetMapping("/{id}")
    public Sacola getSacolaById(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }

    @PostMapping
    public Item addItem(@RequestBody ItemDto itemDto){
        return sacolaService.inserirItemSacola(itemDto);
    }

    @PatchMapping("/closebag/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento) {
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }
}
