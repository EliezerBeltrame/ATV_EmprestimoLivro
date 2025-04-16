package com.example.Biblioteca.DTO;

import com.example.Biblioteca.Entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {
    private long id;
    private String nome;
    private String sobrenome;
    private String cpf;

    // Método para converter o DTO para Entidade (Cliente)
    public Cliente toCliente(){
        return new Cliente(
                this.id,
                this.nome,
                this.sobrenome,
                this.cpf
        );
    }


    public  ClienteDTO fromCliente(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getSobrenome(),
                cliente.getCpf()
        );
    }
}
