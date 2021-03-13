package br.com.zup.desafio.mercadolivre.sistemasexterno;

import com.sun.istack.NotNull;

public class NotaFiscalRequest {
	
	@NotNull
	private Long idCompra;
	@NotNull
	private Long idComprador;
	
	@Deprecated
	public NotaFiscalRequest() {}

	public NotaFiscalRequest(Long idCompra, Long idComprador) {
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		return "NotaFiscalRequest [idCompra=" + idCompra + ", idComprador=" + idComprador + "]";
	}

	public Long getIdCompra() {
		return idCompra;
	}
	
	public Long getIdComprador() {
		return idComprador;
	}

}
