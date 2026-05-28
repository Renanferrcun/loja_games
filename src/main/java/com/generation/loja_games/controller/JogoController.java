package com.generation.loja_games.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.loja_games.model.JogoModel;
import com.generation.loja_games.repository.CategoriaRepository;
import com.generation.loja_games.repository.JogoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class JogoController {
	
	@Autowired
	private JogoRepository jogoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<JogoModel>>getAll() {
		return ResponseEntity.ok(jogoRepository.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JogoModel>getById(@PathVariable Long id) {
		return jogoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<JogoModel>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(jogoRepository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	
	@PostMapping
	public ResponseEntity<JogoModel> post(@Valid @RequestBody JogoModel jogos) {
		
		if (jogoRepository.existsById(jogos.getCategoria().getId())) {
			
			jogos.setId(null);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(jogoRepository.save(jogos));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);	
	}
	
	@PutMapping
	public ResponseEntity<JogoModel> put(@Valid @RequestBody JogoModel jogos) {
		
		if (jogoRepository.existsById(jogos.getId())) {
			
			if (categoriaRepository.existsById(jogos.getCategoria().getId())) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(jogoRepository.save(jogos));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<JogoModel> jogo = jogoRepository.findById(id);
		
		if(jogo.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);	
		
		jogoRepository.deleteById(id);
		
	}
	

}
