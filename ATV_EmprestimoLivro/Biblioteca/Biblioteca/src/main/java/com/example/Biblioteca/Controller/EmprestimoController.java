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
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTOResponse> getById(@PathVariable Long id){
        Optional<EmprestimoDTOResponse> emprestimoDTOOptional = emprestimoService.getById(id);
        if(emprestimoDTOOptional.isPresent()){
            return ResponseEntity.ok(emprestimoDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
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
        Optional<EmprestimoDTOResponse> emprestimoDTOOptional = emprestimoService.update(id, emprestimoDTORequest);
        if (emprestimoDTOOptional.isPresent()) {  // Verificando se o Optional contém um valor
            return ResponseEntity.ok(emprestimoDTOOptional.get()); // Retorna o valor do Optional
        } else {
            return ResponseEntity.notFound().build();  // Caso não tenha encontrado, retorna 404
        }
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
