package com.mycompany.tpdsw.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.tpdsw.dto.ClienteDto;
import com.mycompany.tpdsw.exception.ClienteNoEncontradoException;
import com.mycompany.tpdsw.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<ClienteDto> clientes = clienteService.findAllActive();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws ClienteNoEncontradoException {
        Optional<ClienteDto> clienteDto = Optional.of(clienteService.findByIdAndActive(id));
        if (clienteDto.isPresent()) {
            return ResponseEntity.ok(clienteDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClienteDto clienteDto) throws URISyntaxException {
        if (!clienteDto.getId().equals(null)) {
            return ResponseEntity.badRequest().build();
        }
        clienteService.save(clienteDto);
        return ResponseEntity.created(new URI("/api/cliente/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClienteDto clienteDto)
            throws ClienteNoEncontradoException {
        Optional<ClienteDto> clienteEncontradoDto = Optional.of(clienteService.findByIdAndActive(id));
        if (clienteEncontradoDto.isPresent()) {
            clienteService.update(clienteDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws ClienteNoEncontradoException {
        Optional<ClienteDto> clienteDto = Optional.of(clienteService.findByIdAndActive(id));
        if (clienteDto.isPresent()) {
            clienteService.delete(clienteDto.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}