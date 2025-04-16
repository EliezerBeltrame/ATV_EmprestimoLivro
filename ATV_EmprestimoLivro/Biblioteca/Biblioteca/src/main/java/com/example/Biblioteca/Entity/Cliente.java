package com.example.Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity // Cria a tabela no banco de dados
@Data // Com o Lombok cria os metodos get/setter
@AllArgsConstructor // cria construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio (necessário para o Hibernet)

public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private  String sobrenome;
    @Column(unique = true)// serve para não ter numero de cpf repetido
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    @jsonBackReference
    private set<Emprestimo> emprestimo;
    // estrutura Set tem o mesmo funcionamento do List, porém evitando a duplicidade de valores

    public Cliente(long id, String nome, String sobrenome, String cpf, Emprestimo emprestimo) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;

    }
}
