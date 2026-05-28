package com.generation.loja_games.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_jogos")
public class JogoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Nome é obrigátorio.")
	@Size(min = 1, max = 60, message = "O atributo Nome deve conter no minimo 1 e no máximo 60 caracterres.")
	private String nome;
	
	@NotNull(message = "O atributo Preço é obrigatório.")
	@DecimalMin(value = "0.01", message = "O atributo Preço deve ser um valor positivo.")
	private Double preco;
	
	@ManyToOne
	@JsonIgnoreProperties("jogos") // Define que a propriedade jogos da classe CategoriaModel deve ser ignorada na serialização para evitar loop infinito
	private CategoriaModel categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}
	
	

}
