package com.example.Biblioteca.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String sobrenome;

    @Column(unique = true) // CPF único
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    @JsonBackReference // Correção da anotação
    private Set<Emprestimo> emprestimos; // Correção no tipo de Set

    // Construtor adicional corrigido
    public Cliente(long id, String nome, String sobrenome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }
}
