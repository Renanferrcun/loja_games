package com.generation.loja_games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.loja_games.model.JogoModel;

public interface JogoRepository extends JpaRepository<JogoModel, Long> {
    
    public List<JogoModel> findAllByNomeContainingIgnoreCase(String nome);

}
