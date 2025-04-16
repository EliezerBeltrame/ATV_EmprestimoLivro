package com.example.Biblioteca.Service;

import com.example.Biblioteca.DTO.LivroDTO;
import com.example.Biblioteca.Entity.Livro;
import com.example.Biblioteca.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    // Converte de LivroDTO para Livro
    public Livro fromDTO(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setNome(livroDTO.getNome());
        livro.setISBN(livroDTO.getISBN());
        livro.setId(livroDTO.getId());
        livro.setGenero(livroDTO.getGenero()); // Faltava adicionar o genero

        return livro;
    }

    // Converte de Livro para LivroDTO
    public LivroDTO toDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setNome(livro.getNome());
        livroDTO.setAutor(livro.getAutor());
        livroDTO.setISBN(livro.getIsbn());
        livroDTO.setGenero(livro.getGenero()); // Faltava adicionar o genero

        return livroDTO;
    }

    // Busca todos os livros
    public List<Livro> getAll() {
        return livroRepository.findAll();
    }

    // Busca um livro pelo ID
    public Optional<LivroDTO> getById(Long id) {
        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if (optionalLivro.isPresent()) {
            return Optional.of(this.toDTO(optionalLivro.get()));
        } else {
            return Optional.empty(); // Caso não encontre
        }
    }

    // Salva um novo livro
    public LivroDTO saveDto(LivroDTO livroDTO) {
        Livro livro = this.fromDTO(livroDTO);
        Livro livroBd = livroRepository.save(livro);
        return this.toDTO(livroBd);
    }

    // Atualiza um livro existente
    public Optional<LivroDTO> updateLivro(Long id, LivroDTO livroDTO) {
        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if (optionalLivro.isPresent()) {
            Livro livro = optionalLivro.get();
            livro.setNome(livroDTO.getNome());
            livro.setAutor(livroDTO.getAutor()); // Corrigido o acesso ao autor
            livro.setGenero(livroDTO.getGenero()); // Corrigido o acesso ao genero

            Livro livroUpdated = livroRepository.save(livro);

            return Optional.of(this.toDTO(livroUpdated));
        } else {
            return Optional.empty();
        }
    }

    // Deleta um livro pelo ID
    public boolean delete(Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
