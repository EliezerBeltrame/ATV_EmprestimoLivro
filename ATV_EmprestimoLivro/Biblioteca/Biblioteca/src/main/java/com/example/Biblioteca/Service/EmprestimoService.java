package com.example.Biblioteca.Service;

import com.example.Biblioteca.DTO.EmprestimoDTORequest;
import com.example.Biblioteca.DTO.EmprestimoDTOResponse;
import com.example.Biblioteca.Entity.Emprestimo;
import com.example.Biblioteca.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // converte de EmprestimoDTORequest para Emprestimo
    public Emprestimo fromDTO(EmprestimoDTORequest emprestimoDTORequest) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setData_inicio(emprestimoDTORequest.getData_inicio());
        emprestimo.setData_final(emprestimoDTORequest.getData_final());
        emprestimo.setCliente(emprestimoDTORequest.getCliente()); // Caso esteja incluindo o cliente
        emprestimo.setLivros(emprestimoDTORequest.getLivros()); // Caso esteja incluindo livros

        return emprestimo;
    }

    // converte de Emprestimo para EmprestimoDTOResponse
    public EmprestimoDTOResponse toDTO(Emprestimo emprestimo) {
        EmprestimoDTOResponse emprestimoDTOResponse = new EmprestimoDTOResponse();
        emprestimoDTOResponse.setId(emprestimo.getId());
        emprestimoDTOResponse.setData_inicio(emprestimo.getData_inicio());
        emprestimoDTOResponse.setData_final(emprestimo.getData_final());
        emprestimoDTOResponse.setCliente(emprestimo.getCliente()); // Caso necessário
        emprestimoDTOResponse.setLivros(emprestimo.getLivros()); // Caso necessário

        return emprestimoDTOResponse;
    }

    // Buscar todos os empréstimos
    public List<Emprestimo> getAll() {
        return emprestimoRepository.findAll();
    }

    // Buscar um empréstimo pelo ID
    public Optional<EmprestimoDTOResponse> getById(Long id)
