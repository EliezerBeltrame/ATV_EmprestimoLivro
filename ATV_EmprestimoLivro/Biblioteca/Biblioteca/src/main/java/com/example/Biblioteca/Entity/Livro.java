package com.example.Biblioteca.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    private long isbn;

    private String genero;
    @ManyToOne
    @JoinColumn(name = "idEmprestimo", referencedColumnName = "idEmprestimo")
    @JsonIgnoreProperties("livro")
    private Emprestimo emprestimo;

}
