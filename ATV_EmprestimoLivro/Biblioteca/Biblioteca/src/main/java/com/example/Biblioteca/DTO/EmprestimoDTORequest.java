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

    // Método para converter de EmprestimoDTORequest para Emprestimo (Entidade)
    public Emprestimo toEmprestimo() {
        return new Emprestimo(
                this.id,
                this.data_inicio,
                this.data_final,
                this.cliente,
                this.livros
        );
    }

    // Método para converter de Emprestimo (Entidade) para EmprestimoDTO
    public EmprestimoDTO toEmprestimoDTO(Emprestimo emprestimo) {
        return new EmprestimoDTO(
                emprestimo.getId(),
                emprestimo.getData_inicio(),
                emprestimo.getData_final(),
                emprestimo.getCliente(),
                emprestimo.getLivros()
        );
    }
}
