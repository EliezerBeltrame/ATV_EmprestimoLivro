package com.example.Biblioteca.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class LivroDTO implements Serializable {
    private long id;

    private String nome;

    private String autor;

    private long isbn;

    private String genero;

    public LivroDTO(){

    }
}
