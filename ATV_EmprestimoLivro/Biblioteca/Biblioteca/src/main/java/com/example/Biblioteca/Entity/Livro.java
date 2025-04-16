package com.example.Biblioteca.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importando corretamente
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity // Cria a tabela no banco de dados
@Data // Com o Lombok cria os métodos get/setter
@AllArgsConstructor // Cria construtor com todos os atributos
@NoArgsConstructor // Cria um construtor vazio (necessário para o Hibernate)
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Melhor usar Long ao invés de long

    private String nome;
    private String autor;

    private Long ISBN;  // Mudança para Long, permitindo valores nulos

    @Column(unique = true)
    private String genero;

    @ManyToMany(mappedBy = "livros")
    @JsonIgnoreProperties("livros") // Ignora a propriedade 'livros' na serialização de 'Emprestimo'
    private Set<Emprestimo> emprestimos;

    // Construtor personalizado, se necessário
    public Livro(Long id, String nome, String autor, Long ISBN, String genero) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.ISBN = ISBN;
        this.genero = genero;
        this.emprestimos = emprestimos;
    }
}
