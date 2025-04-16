package com.example.Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity // Cria a tabela no banco de dados
@Data // Com o Lombok cria os metodos get/setter
@AllArgsConstructor // cria construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio (necessário para o Hibernet)

public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data_inicio;
    private LocalDate data_final;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    @JsonManagedReference
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "emprestimo_livro",
            joinColumns = @JoinColumn(name = "emprestimo_id"),
           inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private Set<Livro> livros;

}
