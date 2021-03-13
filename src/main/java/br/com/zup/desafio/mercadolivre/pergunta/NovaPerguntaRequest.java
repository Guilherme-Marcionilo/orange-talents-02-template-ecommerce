package br.com.zup.desafio.mercadolivre.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;
	
	@Deprecated
	public NovaPerguntaRequest() {}

	public NovaPerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toModel(@NotNull @Valid Usuario interessada,@Valid @NotNull Produto produto) {
		return new Pergunta(titulo,interessada,produto);
	}

	public String getTitulo() {
		return titulo;
	}
	
}
