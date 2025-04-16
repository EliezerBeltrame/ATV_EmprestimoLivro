package com.example.Biblioteca.Service;

import com.example.Biblioteca.DTO.EmprestimoDTORequest;
import com.example.Biblioteca.DTO.EmprestimoDTOResponse;
import com.example.Biblioteca.Entity.Emprestimo;
import com.example.Biblioteca.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // Converte de EmprestimoDTORequest para Emprestimo
    public Emprestimo fromDTO(EmprestimoDTORequest emprestimoDTORequest) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setData_inicio(emprestimoDTORequest.getData_inicio());
        emprestimo.setData_final(emprestimoDTORequest.getData_final());
        emprestimo.setCliente(emprestimoDTORequest.getCliente()); // Cliente vindo do DTO
        emprestimo.setLivros(emprestimoDTORequest.getLivros()); // Livros vindo do DTO

        return emprestimo;
    }

    // Converte de Emprestimo para EmprestimoDTOResponse
    public EmprestimoDTOResponse toDTO(Emprestimo emprestimo) {
        EmprestimoDTOResponse emprestimoDTOResponse = new EmprestimoDTOResponse();
        emprestimoDTOResponse.setId(emprestimo.getId());
        emprestimoDTOResponse.setData_inicio(emprestimo.getData_inicio());
        emprestimoDTOResponse.setData_final(emprestimo.getData_final());
        // Você pode adicionar outros campos na conversão se necessário

        return emprestimoDTOResponse;
    }

    // Busca todos os empréstimos e converte para DTO
    public List<EmprestimoDTOResponse> getAll() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        // Convertendo lista de Emprestimos para EmprestimoDTOResponse
        return emprestimos.stream()
                .map(this::toDTO) // Convertendo cada Emprestimo em EmprestimoDTOResponse
                .collect(Collectors.toList());
    }

    // Buscar um empréstimo pelo ID e retornar o DTO
    public Optional<EmprestimoDTOResponse> getById(Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()) {
            return Optional.of(this.toDTO(emprestimo.get())); // Converte o Emprestimo para EmprestimoDTOResponse
        } else {
            return Optional.empty(); // Caso não encontre o empréstimo
        }
    }

    // Salva um empréstimo, criando um novo
    public EmprestimoDTOResponse save(EmprestimoDTORequest emprestimoDTORequest) {
        Emprestimo emprestimo = this.fromDTO(emprestimoDTORequest); // Converte o DTO para Entidade
        Emprestimo emprestimoSaved = emprestimoRepository.save(emprestimo); // Salva no banco
        return this.toDTO(emprestimoSaved); // Retorna o DTO do empréstimo salvo
    }

    // Atualiza um empréstimo existente
    public Optional<EmprestimoDTOResponse> update(Long id, EmprestimoDTORequest emprestimoDTORequest) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);
        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            emprestimo.setData_inicio(emprestimoDTORequest.getData_inicio());
            emprestimo.setData_final(emprestimoDTORequest.getData_final());
            emprestimo.setCliente(emprestimoDTORequest.getCliente());
            emprestimo.setLivros(emprestimoDTORequest.getLivros());

            Emprestimo updatedEmprestimo = emprestimoRepository.save(emprestimo); // Atualiza o empréstimo no banco
            return Optional.of(this.toDTO(updatedEmprestimo)); // Retorna o DTO atualizado
        } else {
            return Optional.empty(); // Caso o empréstimo não seja encontrado
        }
    }

    // Deleta um empréstimo pelo ID
    public boolean delete(Long id) {
        if (emprestimoRepository.existsById(id)) {
            emprestimoRepository.deleteById(id); // Deleta o empréstimo
            return true;
        } else {
            return false; // Caso o empréstimo não exista
        }
    }
}
