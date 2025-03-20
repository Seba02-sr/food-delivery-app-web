package com.mycompany.tpdsw.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.tpdsw.dto.ItemMenuDto;
import com.mycompany.tpdsw.service.ItemMenuService;

@Controller
@RequestMapping("/item-menu")
public class ItemMenuController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ItemMenuController.class);

    @Autowired
    private ItemMenuService itemMenuService;

    @GetMapping("/find/{id}")
    public ResponseEntity<List<ItemMenuDto>> mostrarItemMenuPorVendedorId(@PathVariable Integer id) {
        List<ItemMenuDto> items = itemMenuService.findActiveByIdVendedor(id);
        logger.info("Items Menu recuperados {}", items);
        if (items.isEmpty()) {
            logger.info("error, no se encontraron items");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    // Thyemeleaf
    @GetMapping("/{id}")
    public String mostrarItemMenuPorVendedorId(@PathVariable Integer id, Model model) {
        List<ItemMenuDto> items = itemMenuService.findActiveByIdVendedor(id);
        logger.info("Items Menu recuperados {}", items);

        // Nombres de la categorias
        // List<String> categorias = categoriaController.findNameOfAllCategoria();
        // logger.info("Categorias recuperadas {}", categorias);

        // Agrupar los items por categoría
        Map<String, List<ItemMenuDto>> itemsPorCategoria = items.stream()
                .collect(Collectors.groupingBy(item -> item.getCategoria()));
        logger.info("Item agupado por categoria: {}", itemsPorCategoria);

        if (items != null && !items.isEmpty()) {
            model.addAttribute("itemsPorCategoria", itemsPorCategoria);
            // model.addAttribute("categorias", categorias);
            return "items-vendedor";
        } else {
            return "items-vendedor-no-encontrado";
        }
    }
}
