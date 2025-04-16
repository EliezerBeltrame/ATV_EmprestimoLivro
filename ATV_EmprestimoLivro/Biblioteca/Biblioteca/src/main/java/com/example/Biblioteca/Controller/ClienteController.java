package com.example.Biblioteca.Controller;

import com.example.Biblioteca.DTO.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para buscar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return clienteService.getAll();

    }

    // Endpoint para buscar um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        Optional<ClienteDTO> clienteDTOOptional = clienteService.getById(id);
        if(clienteDTOOptional.isPresent()){
            return ResponseEntity.ok(clienteDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para criar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteDTOSave = clienteService.createCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTOSave);
    }

    // Endpoint para atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteDTO> clienteAtualizado = clienteService.updateCliente(id, clienteDTO);
        if(clienteAtualizado.isPresent()){
            return ResponseEntity.ok(clienteAtualizado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (clienteService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
