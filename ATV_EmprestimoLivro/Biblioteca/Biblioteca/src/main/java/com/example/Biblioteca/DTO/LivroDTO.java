package com.example.Biblioteca.DTO;

import com.example.Biblioteca.Entity.Livro;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LivroDTO  {
    private long id
    private String nome;
    private String autor;
    private long ISBN;
    private String genero;

    public Livro toLivro(){
        return  new Livro(
                this.id,
                this.nome,
                this.autor,
                this.ISBN,
                this.genero
        );
    }
    public LivroDTO fromLivro(livro livro){
        return new LivroDTO(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getISBN(),
                livro.getGenero()
        );
    }
}
