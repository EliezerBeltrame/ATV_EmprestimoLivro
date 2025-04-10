package com.example.Biblioteca.DTO;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClienteDTO {
    private long id;
    private String nome;
    private  String sobrenome;
    private String cpf;

    public ClienteDTO(){
    }


}
