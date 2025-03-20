package com.mycompany.tpdsw.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.tpdsw.dto.VendedorDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.exception.VendedorNoEncontradoException;
import com.mycompany.tpdsw.service.VendedorService;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(VendedorController.class);

    @Autowired
    private VendedorService vendedorService;

    // JSON
    @GetMapping("/findAll")
    public ResponseEntity<List<VendedorDto>> getVendedores() {
        List<VendedorDto> vendedores = vendedorService.findAllByActivoTrue();
        if (vendedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vendedores);
    }

    // Thymeleaf
    @GetMapping("/list")
    public String getVendedoresPage(Model model) {
        List<VendedorDto> vendedores = vendedorService.findAllByActivoTrue();
        logger.info("Vendedores recuperados {}", vendedores);
        model.addAttribute("vendedores", vendedores);

        if (vendedores.isEmpty()) {
            return "lista-vendedores-no-encontrada";
        }
        return "lista-vendedores";
    }

    @GetMapping("/findByNombre")
    public String getMethodName(@RequestParam String name, Model model) {

        try {
            List<VendedorDto> vendedores = vendedorService.findByNombre(name);
            logger.info("Vendedores recuperados {}", vendedores);
            model.addAttribute("vendedores", vendedores);
            model.addAttribute("nombreBuscado", name);
            return "lista-vendedores";
        } catch (VendedorNoEncontradoException e) {
            return "lista-vendedores-no-encontrada";
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<VendedorDto> findById(@PathVariable Integer id) throws VendedorNoEncontradoException {
        Optional<VendedorDto> vendedorDto = Optional.of(vendedorService.findById(id));
        if (vendedorDto.isPresent()) {
            return ResponseEntity.ok(vendedorDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveVendedor(@RequestBody VendedorDto vendedorDto) {
        if (vendedorDto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        vendedorService.save(vendedorDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VendedorDto vendedorDto)
            throws ClienteNoEncontradoException, VendedorNoEncontradoException {
        Optional<VendedorDto> vendedorEncontrado = Optional.of(vendedorService.findById(id));
        if (vendedorEncontrado.isPresent()) {
            vendedorService.update(vendedorDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws VendedorNoEncontradoException {
        Optional<VendedorDto> vendedorDto = Optional.of(vendedorService.findById(id));
        if (vendedorDto.isPresent()) {
            vendedorService.delete(vendedorDto.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
