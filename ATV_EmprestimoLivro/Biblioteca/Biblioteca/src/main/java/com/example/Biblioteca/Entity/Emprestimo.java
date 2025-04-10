package com.example.Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Entity // Cria a tabela no banco de dados
@Data // Com o Lombok cria os metodos get/setter
@AllArgsConstructor // cria construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio (necessário para o Hibernet)

public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data_inicio;

    private LocalDate data_final;

    @OneToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Emprestimo emprestimo;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    private List<Livro> livro;

}
