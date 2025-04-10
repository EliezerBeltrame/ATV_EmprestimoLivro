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
public class emprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // teremos dois objetos DTO para ser trabalhado devido
    // a entrada das informações de alunos não serem feitas pelo Emprestimo, ou seja o EmprestimoDTORequest nao tem o aluno
    // no caso da saida de dados, ao retornar um emprestimo será retornado o seus alunos, assim será utilizado EmprestimoDTOResponse

    // converte de EmprestimoDTORequest para Emprestimo
    public Emprestimo fromDTO(EmprestimoDTORequest emprestimoDTORequest){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setData_inicio(emprestimoDTORequest.get());
        emprestimo.setData_final(emprestimoDTORequest.get);

        return emprestimo;
    }

    // converte de Emprestimo para EmprestimoDTO response
    public EmprestimoDTOResponse toDTO(Emprestimo emprestimo){
        EmprestimoDTOResponse emprestimoDTOResponse = new EmprestimoDTOResponse();
        emprestimoDTOResponse.setEmprestimo(emprestimo.getEmprestimo());
        emprestimoDTOResponse.setData_final(emprestimo.getData_final());
        emprestimoDTOResponse.setData_inicio(emprestimo.getData_inicio());
        emprestimoDTOResponse.setId(emprestimo.getId());


        return emprestimoDTOResponse;
    }

    public List<Emprestimo> getAll(){
        return emprestimoRepository.findAll();
    }

    public Optional<EmprestimoDTOResponse> getById(Long id){
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepository.findById(id);
        if(optionalEmprestimo.isPresent()){// verifica se encontrou algum professor
            return Optional.of(this.toDTO(Emprestimo.get()));
        }else {
            return Optional.empty(); // um objeto Optional vazio.
        }
//        return professorRepository.findById(id).map(this::toDTO);
    }

    public EmprestimoDTOResponse saveDto(EmprestimoDTORequest cursoDTORequest){
        Emprestimo emprestimo = this.fromDTO(cursoDTORequest);
        Emprestimo cursoBd = emprestimoRepository.save(emprestimo);
        return this.toDTO(cursoBd);
    }

    public Optional<EmprestimoDTOResponse> updateEmprestimo(Long id, EmprestimoDTORequest cursoDTORequest){
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepository.findById(id);
        if(optionalEmprestimo.isPresent()){
            Emprestimo emprestimo = Emprestimo.get();
            emprestimo.setId(cursoDTORequest.getId());
            emprestimo.setData_inicio(cursoDTORequest.getData_inicio());
            emprestimo.setData_final(cursoDTORequest.getData_final());

            Emprestimo emprestimoUpdate = emprestimoRepository.save(emprestimo);

            return Optional.of(this.toDTO(emprestimoUpdate));
        }else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(emprestimoRepository.existsById(id)){
            emprestimoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

}

