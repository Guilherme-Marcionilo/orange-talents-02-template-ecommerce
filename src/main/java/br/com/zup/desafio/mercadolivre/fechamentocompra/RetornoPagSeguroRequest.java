package br.com.zup.desafio.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento {
	
	
	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagseguro status;
	
	@Deprecated
	public RetornoPagSeguroRequest() {}
	
	public RetornoPagSeguroRequest(String idTransacao,
			StatusRetornoPagseguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusRetornoPagseguro getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "RetornoPagSeguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public Transacao toTransacao(Compra compra) {
		
		return new Transacao(status.normaliza(),this.idTransacao,compra);
		
	}

}
