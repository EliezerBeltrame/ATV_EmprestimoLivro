package com.example.Biblioteca.Service;

import com.example.Biblioteca.DTO.LivroDTO;
import com.example.Biblioteca.Entity.Livro;
import com.example.Biblioteca.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class livroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro fromDTO(LivroDTO livroDTO){
        Livro livro = new Livro();
        livro.setNome(livroDTO.get());
        livro.setIsbn(livroDTO.get());
        livro.setId(livroDTO.get());


        return livro;
    }

    public LivroDTO toDTO(Livro livro){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.set(livro.getId());
        livroDTO.set(livro.getNome());
        livroDTO.set(livro.getAutor());
        livroDTO.set(livro.getGenero());

        return livroDTO;
    }

    public List<Livro> getAll(){
        return livroRepository.findAll();
    }

    public Optional<LivroDTO> getById(Long id){
        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if(optionalLivro.isPresent()){
            return Optional.of(this.toDTO(Livro.get()));
        }else {
            return Optional.empty();
        }
//        return livroRepository.findById(id).map(this::toDTO);
    }

    public LivroDTO saveDto(LivroDTO livroDTO){
        Livro livro = this.fromDTO(livroDTO);
        Livro livroBd = livroRepository.save(livro);
        return this.toDTO(livroBd);
    }

    public Optional<LivroDTO> updateLivro(Long id, LivroDTO livroDTO){
        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if(optionalLivro.isPresent()){
            Livro livro = Livro.get();
            livro.setNome(livroDTO.getNome());
            livro.setAutor(livroDTO.get());
            livro.setGenero(livroDTO.get());

            Livro alunoUpdate = livroRepository.save(livro);

            return Optional.of(this.toDTO(alunoUpdate));
        }else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(livroRepository.existsById(id)){
            livroRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

}
