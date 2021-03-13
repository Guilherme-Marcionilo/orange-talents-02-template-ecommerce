package br.com.zup.desafio.mercadolivre.sistemasexterno;

import com.sun.istack.NotNull;

public class RankingRequest {
	
	@NotNull
	private Long idCompra;
	@NotNull
	private Long idDonoProduto;

	@Deprecated
	public RankingRequest() {}
	
	public RankingRequest(Long idCompra, Long idDonoProduto) {
		this.idCompra = idCompra;
		this.idDonoProduto = idDonoProduto;
	}

	@Override
	public String toString() {
		return "RankingRequest [idCompra=" + idCompra + ", idDonoProduto=" + idDonoProduto + "]";
	}


}
