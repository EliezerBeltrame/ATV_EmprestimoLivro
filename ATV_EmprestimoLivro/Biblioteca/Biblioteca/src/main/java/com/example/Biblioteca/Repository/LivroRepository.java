package com.example.Biblioteca.Repository;

import com.example.Biblioteca.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findAllByNome(String nome);// buscar pelo nome
}
