package com.example.Biblioteca.Controller;

import com.example.Biblioteca.DTO.EmprestimoDTORequest;
import com.example.Biblioteca.DTO.EmprestimoDTOResponse;
import com.example.Biblioteca.Service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    // Endpoint para buscar todos os empréstimos
    @GetMapping
    public ResponseEntity<List<EmprestimoDTOResponse>> getAll() {
        List<EmprestimoDTOResponse> emprestimoDTOList = emprestimoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoDTOList);
    }

    // Endpoint para buscar um empréstimo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTOResponse> getById(@PathVariable Long id) {
        Optional<EmprestimoDTOResponse> emprestimoDTO = emprestimoService.getById(id);
        return emprestimoDTO.map(ResponseEntity::ok) // Se encontrado, retorna 200 OK com o DTO
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Caso contrário, 404
    }

    // Endpoint para criar um novo empréstimo
    @PostMapping
    public ResponseEntity<EmprestimoDTOResponse> create(@RequestBody EmprestimoDTORequest emprestimoDTORequest) {
        EmprestimoDTOResponse emprestimo = emprestimoService.save(emprestimoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
    }

    // Endpoint para atualizar um empréstimo existente
    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTOResponse> update(@PathVariable Long id, @RequestBody EmprestimoDTORequest emprestimoDTORequest) {
        Optional<EmprestimoDTOResponse> emprestimoDTOOptional = emprestimoService.updateEmprestimo(id, emprestimoDTORequest);
        return emprestimoDTOOptional.map(ResponseEntity::ok) // Se encontrado, retorna 200 OK com o DTO
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Caso contrário, 404
    }

    // Endpoint para deletar um empréstimo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (emprestimoService.delete(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found
        }
    }
}
