package com.example.Biblioteca.Controller;

import com.example.Biblioteca.DTO.EmprestimoDTO;
import com.example.Biblioteca.Entity.Emprestimo;
import com.example.Biblioteca.Service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos") // Usar plural é mais RESTful
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    // Buscar todos os empréstimos
    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {
        List<Emprestimo> emprestimos = emprestimoService.getAll();
        return ResponseEntity.ok(emprestimos);
    }

    // Buscar empréstimo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> buscarPorId(@PathVariable Long id) {
        return emprestimoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar um novo empréstimo
    @PostMapping
    public ResponseEntity<EmprestimoDTO> criar(@RequestBody EmprestimoDTO emprestimoDto) {
        EmprestimoDTO novoEmprestimo = emprestimoService.save(emprestimoDto);
        return ResponseEntity.status(201).body(novoEmprestimo);
    }

    // Atualizar um empréstimo existente
    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> atualizar(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO) {
        return emprestimoService.updateEmprestimo(id, emprestimoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar um empréstimo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = emprestimoService.delete(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
