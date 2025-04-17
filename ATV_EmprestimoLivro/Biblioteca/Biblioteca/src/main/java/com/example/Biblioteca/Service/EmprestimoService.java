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

    public List<Emprestimo> getAllEmprestimos(){
        return emprestimoRepository.findAll();//busca todos os emprestimos
    }

    // Busca todos os empréstimos e converte para DTO
    public Optional<EmprestimoDTOResponse> getAll(Long id) {
        Optional<Emprestimo> emprestimosOptional = emprestimoRepository.findById(id);
        if(emprestimosOptional.isPresent()){
            EmprestimoDTOResponse emprestimoDTOResponse = new EmprestimoDTOResponse();
            return Optional.of(emprestimoDTOResponse.fromEmprestimo(emprestimosOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    // Salva um empréstimo, criando um novo
    public EmprestimoDTOResponse save(EmprestimoDTORequest emprestimoDTORequest) {
        Emprestimo emprestimo = EmprestimoDTOResponse.toEmprestimoResponse();
        emprestimo= emprestimoRepository.save(emprestimo);
        return EmprestimoDTOResponse.fromEmprestimo(emprestimo);
    }

    // Atualiza um empréstimo existente
    public Optional<EmprestimoDTOResponse> update(Long id, EmprestimoDTORequest emprestimoDTORequest) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);
        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            emprestimo.setData_inicio(emprestimoDTORequest.getData_inicio());
            emprestimo.setData_final(emprestimoDTORequest.getData_final());//pode ou não ter
            emprestimo.setCliente(emprestimoDTORequest.getCliente()); // aqui tambem
            emprestimo.setLivros(emprestimoDTORequest.getLivros());

            Emprestimo  emprestimoRepository.save(emprestimo); // Atualiza o empréstimo no banco
            return Optional.of(this.toDTO(updatedEmprestimo)); // Retorna o DTO atualizado
        } else {
            return Optional.empty(); // Caso o empréstimo não seja encontrado
        }
    }

    // Converte de EmprestimoDTORequest para Emprestimo
    public Emprestimo fromDTO(EmprestimoDTORequest emprestimoDTORequest) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setData_inicio(emprestimoDTORequest.getData_inicio());
        emprestimo.setData_final(emprestimoDTORequest.getData_final());
        emprestimo.setCliente(emprestimoDTORequest.getCliente()); // Cliente vindo do DTO


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



    // Buscar um empréstimo pelo ID e retornar o DTO
    public Optional<EmprestimoDTOResponse> getById(Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        if (emprestimo.isPresent()) {
            return Optional.of(this.toDTO(emprestimo.get())); // Converte o Emprestimo para EmprestimoDTOResponse
        } else {
            return Optional.empty(); // Caso não encontre o empréstimo
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
