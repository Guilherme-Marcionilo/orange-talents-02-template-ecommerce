package br.com.zup.desafio.mercadolivre.fechamentocompra;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class NovaCompraRequest {

	@Positive
	private int quantidade;

	@NotNull
	private Long idProduto;
	
	@NotNull
	private GatewayPagamento gateway;

	@Deprecated
	public NovaCompraRequest() {
	}

	public NovaCompraRequest(@Positive int quantidade, Long idProduto, GatewayPagamento gateway) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
}
