package com.generation.loja_games.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")

public class CategoriaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Genêro é obrigatório.")
	@Size(min = 1, max = 30, message = "O atributo Genêro deve conter no minimo 1 e no máximo 30 caracterres.")
	private Long genero;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties(value = "categoria", allowSetters = true)
	private List<JogoModel>  jogoModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGenero() {
		return genero;
	}

	public void setGenero(Long genero) {
		this.genero = genero;
	}

	public List<JogoModel> getJogoModel() {
		return jogoModel;
	}

	public void setJogoModel(List<JogoModel> jogoModel) {
		this.jogoModel = jogoModel;
	}
	
	
}
