package com.example.Biblioteca.DTO;

import com.example.Biblioteca.Entity.Cliente;
import com.example.Biblioteca.Entity.Emprestimo;
import com.example.Biblioteca.Entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmprestimoDTORequest implements Serializable {
    private long id;
    private LocalDate data_inicio;
    private LocalDate data_final;
    private Cliente cliente;
    private Set<Livro> livros;

    public Emprestimo toEmprestimo(){
        return new Emprestimo(
                this.id,
                this.dataInicial,
                this.dataFinal,
                this.getCliente(),
                this.livros
        );
    }

    public EmprestimoDTO fromEmprestimo(Emprestimo emprestimo) {
        return new EmprestimoDTO(
                emprestimo.getId(),
                emprestimo.getDataInicial(),
                emprestimo.getDataFinal(),
                emprestimo.getCliente(),
                emprestimo.getLivros()
        );
    }
}
