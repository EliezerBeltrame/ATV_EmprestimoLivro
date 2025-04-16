package com.example.Biblioteca.Controller;

import com.example.Biblioteca.DTO.LivroDTO;
import com.example.Biblioteca.Entity.Livro;
import com.example.Biblioteca.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros") // Usando plural conforme padrão RESTful
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // Buscar todos os livros
    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        List<Livro> livros = livroService.getAll();
        return ResponseEntity.ok(livros);
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        return livroService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar um novo livro
    @PostMapping
    public ResponseEntity<LivroDTO> criar(@RequestBody LivroDTO livroDto) {
        LivroDTO novoLivro = livroService.saveDto(livroDto);
        return ResponseEntity.status(201).body(novoLivro);
    }

    // Atualizar um livro existente
    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        return livroService.updateLivro(id, livroDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar um livro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = livroService.delete(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
