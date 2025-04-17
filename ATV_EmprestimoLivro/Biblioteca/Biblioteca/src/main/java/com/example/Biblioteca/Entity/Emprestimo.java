package com.example.Biblioteca.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference; // Importando corretamente
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity // Cria a tabela no banco de dados
@Data // Com o Lombok cria os métodos getter/setter
@AllArgsConstructor // Cria construtor com todos os atributos
@NoArgsConstructor // Cria um construtor vazio (necessário para o Hibernate)
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data_inicio;
    private LocalDate data_final;

    // Muitos para  Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    // Muitos para Muitos com Livro
    @ManyToMany
    @JoinTable(
            name = "emprestimo_livro", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "emprestimo_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private Set<Livro> livros;

}
