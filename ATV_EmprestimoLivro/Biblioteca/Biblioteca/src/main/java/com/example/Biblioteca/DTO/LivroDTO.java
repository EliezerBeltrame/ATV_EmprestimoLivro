package com.example.Biblioteca.DTO;

import com.example.Biblioteca.Entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO implements Serializable {
    private long id;
    private String nome;
    private String autor;
    private long ISBN;
    private String genero;
    // converte os PodutoDTO em Produto
    // Conversão é necessária para depois das valições e regras de neogico aplicada no DTO transforme para
    // Entidade(Produto) para salvar os dados corretos no banco

    public Livro toLivro() {
        return new Livro(
                this.id,
                this.nome,
                this.autor,
                this.ISBN,
                this.genero
        );
    }

    // converte Produto em ProdutoDTO
    // Conversão necessária porque o usuário
    // não tenha contato com a Entidade do banco de dados, assim matemos a segurança do sistema.
    public LivroDTO fromLivro(Livro livro) {
        return new LivroDTO(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getISBN(),
                livro.getGenero()
        );
    }
}
