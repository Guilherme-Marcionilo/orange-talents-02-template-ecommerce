package br.com.zup.desafio.mercadolivre.detalheproduto;

import br.com.zup.desafio.mercadolivre.produtos.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {
	
	private String nome;
	private String descricao;

	@Deprecated
	public DetalheProdutoCaracteristica() {}
	
	public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
