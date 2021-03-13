package br.com.zup.desafio.mercadolivre.fechamentocompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {
	
	@Min(0)
	@Max(1)
	private int status;
	@NotBlank
	private String idTransacao;

	@Deprecated
	public RetornoPaypalRequest() {}
	
	public RetornoPaypalRequest(@Min(0) @Max(1) int status,
			@NotBlank String idTransacao) {
		
		this.status = status;
		this.idTransacao = idTransacao;
	}
	

	public int getStatus() {
		return status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.ERRO
				: StatusTransacao.SUCESSO;
		
		return new Transacao(statusCalculado, idTransacao, compra);
	}

}
