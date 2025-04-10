package com.example.Biblioteca.DTO;

import com.example.Biblioteca.Entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmprestimoDTORequest {

    private LocalDate data_inicio;
    private LocalDate data_final;
    private Cliente cliente;

    public EmprestimoDTORequest(){
    }

}
