package br.com.zup.desafio.mercadolivre.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.fechamentocompra.Compra;
import br.com.zup.desafio.mercadolivre.fechamentocompra.GatewayPagamento;
import br.com.zup.desafio.mercadolivre.fechamentocompra.RetornoGatewayPagamento;
import br.com.zup.desafio.mercadolivre.fechamentocompra.StatusTransacao;
import br.com.zup.desafio.mercadolivre.fechamentocompra.Transacao;
import br.com.zup.desafio.mercadolivre.produtos.NovaCaracteristicaRequest;
import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

public class TestandoCompraTest {

	@Test
	@DisplayName("aceitar uma transacao com sucesso")
	void deveriaAdicionarUmaTransacaoComSucesso() {
		Compra novaCompra = novaCompra();
		RetornoGatewayPagamento retornoGatewayPagamento = (compra) -> {
			return new Transacao(StatusTransacao.SUCESSO, "1", compra);
		};
		
		novaCompra.adicionaTransacao(retornoGatewayPagamento);
	}
	
	@Test
	@DisplayName("não pode aceitar mais de uma transacao com sucesso")
	void naoDeveriaAdicionarMaisDeUmaTransacaoComSucesso() {
		Compra novaCompra = novaCompra();
		RetornoGatewayPagamento retornoGatewayPagamento = (compra) -> {
			return new Transacao(StatusTransacao.SUCESSO, "1", compra);
		};
		
		novaCompra.adicionaTransacao(retornoGatewayPagamento);
		
		RetornoGatewayPagamento retornoGatewayPagamento2 = (compra) -> {
			return new Transacao(StatusTransacao.SUCESSO, "2", compra);
		};
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			novaCompra.adicionaTransacao(retornoGatewayPagamento2);
		});
	}
	
	@Test
	@DisplayName("não pode aceitar transacao com erro quando ja foi concluido com sucesso")
	void naoPodeAceitarTransacaoComErroQuandoJaFoiConcluidaComSucesso() {
		Compra novaCompra = novaCompra();
		RetornoGatewayPagamento retornoGatewayPagamento = (compra) -> {
			return new Transacao(StatusTransacao.SUCESSO, "1", compra);
		};
		
		novaCompra.adicionaTransacao(retornoGatewayPagamento);
		
		RetornoGatewayPagamento retornoGatewayPagamento2 = (compra) -> {
			return new Transacao(StatusTransacao.ERRO, "2", compra);
		};
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			novaCompra.adicionaTransacao(retornoGatewayPagamento2);
		});
	}
	
	private Compra novaCompra() {
		Categoria categoria = new Categoria("teste");
		Usuario dono = new Usuario("email@email.com", "123456");
		Collection<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
		caracteristicas.add(new NovaCaracteristicaRequest("nome", "descricao"));
		caracteristicas	
				.add(new NovaCaracteristicaRequest("nome1", "descricao"));
		caracteristicas
				.add(new NovaCaracteristicaRequest("nome2", "descricao"));

		Produto produtoASerComprado = new Produto("teste", 100, "descricao",
				BigDecimal.TEN, categoria, dono, caracteristicas);

		Usuario comprador = new Usuario("comprador@email.com",
				"123456");

		GatewayPagamento gatewayPagamento = GatewayPagamento.PAGSEGURO;

		return new Compra(produtoASerComprado, 50, comprador, gatewayPagamento);
	}
	
	
	
}
