package br.com.zup.desafio.mercadolivre.opiniao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

public class NovaOpiniaoRequest {

	@Min (1)
	@Max (5)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@Size(max = 500)
	private String descricao;
	
	@Deprecated
	public NovaOpiniaoRequest() {}

	public NovaOpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo, @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao toModel(@NotNull @Valid Produto produto, Usuario consumidor) {
		return new Opiniao(this.nota, this.titulo, this.descricao,produto,consumidor);
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
