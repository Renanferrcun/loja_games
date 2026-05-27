package com.generation.loja_games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.loja_games.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
    
    public List<CategoriaModel> findAllByGeneroContainingIgnoreCase(String genero);

}
