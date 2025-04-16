package com.example.Biblioteca.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity // Cria a tabela no banco de dados
@Data // Com o Lombok cria os metodos get/setter
@AllArgsConstructor // cria construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio (necessário para o Hibernet)
public class Livro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String autor;
    private long ISBN;
    @Column(unique = true)
    private String genero;
    @ManyToMany(mappedBy = "livros")
    private Set<Emprestimo> emprestimos;

    public Livro(long id, String nome, String autor, long ISBN, String genero, Set<Emprestimo> emprestimos) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.ISBN = ISBN;
        this.genero = genero;
        this.emprestimos = emprestimos;
    }
}
