package com.example.Biblioteca.Controller;

import com.example.Biblioteca.DTO.LivroDTO;
import com.example.Biblioteca.Entity.Livro;
import com.example.Biblioteca.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getAll(@RequestParam(required = false)String nome) {// @RequestParam vai pegar o valor de "?nome=NomeLivro", no caso "NomeLivro" realizar o filtro
            if(nome != null && !nome.isEmpty()){
        }
        return livroService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> getById(@PathVariable Long id) {
        Optional<LivroDTO> livroDTOOptional = livroService.getById(id);
        if(livroDTOOptional.isPresent()){
            return ResponseEntity.ok(livroDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LivroDTO> create(@RequestBody LivroDTO livroDto) {
        LivroDTO livro = livroService.saveDto(livroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        Optional<LivroDTO> livroAtualizado = livroService.updateLivro(id, livroDTO);
        return livroAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (livroService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
