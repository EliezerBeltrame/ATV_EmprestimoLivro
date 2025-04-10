package com.example.Biblioteca.Controller;

import com.example.Biblioteca.DTO.EmprestimoDTO;
import com.example.Biblioteca.Entity.Emprestimo;
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

    @GetMapping
    public ResponseEntity<List<Emprestimo>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> getById(@PathVariable Long id) {
        Optional<EmprestimoDTO> emprestimoDTO = emprestimoService.getById(id);
        if (emprestimoDTO.isPresent()) {
            return ResponseEntity.ok(emprestimoDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> create(@RequestBody EmprestimoDTO emprestimoDto) {
        EmprestimoDTO emprestimo = emprestimoService.save(emprestimoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO) {
        Optional<EmprestimoDTO> emprestimoAtualizado = emprestimoService.updateEmprestimo(id, emprestimoDTO);
        if (emprestimoAtualizado.isPresent()) {
            return ResponseEntity.ok(emprestimoAtualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (emprestimoService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
