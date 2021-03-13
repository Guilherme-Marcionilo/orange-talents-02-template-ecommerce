package br.com.zup.desafio.mercadolivre.produtos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class NovaCaracteristicaRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	@Deprecated
	public NovaCaracteristicaRequest() {
	}

	public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "NovaCaracteristicaRequest [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(nome,descricao, produto);
	}

}
