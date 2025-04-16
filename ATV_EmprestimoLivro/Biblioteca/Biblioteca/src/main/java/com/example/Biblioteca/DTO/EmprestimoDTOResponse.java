package com.example.Biblioteca.DTO;

import com.example.Biblioteca.Entity.Cliente;
import com.example.Biblioteca.Entity.Emprestimo;
import com.example.Biblioteca.Entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class EmprestimoDTOResponse implements Serializable {
    private Long id;
    private LocalDate data_inicio;
    private LocalDate data_final;
    private Cliente cliente;
    public List<Livro> livros;



}
