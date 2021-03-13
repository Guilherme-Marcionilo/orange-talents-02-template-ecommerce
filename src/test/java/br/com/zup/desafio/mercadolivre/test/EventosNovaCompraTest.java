package br.com.zup.desafio.mercadolivre.test;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.fechamentocompra.Compra;
import br.com.zup.desafio.mercadolivre.fechamentocompra.EventoCompraSucesso;
import br.com.zup.desafio.mercadolivre.fechamentocompra.EventosNovaCompra;
import br.com.zup.desafio.mercadolivre.fechamentocompra.GatewayPagamento;
import br.com.zup.desafio.mercadolivre.fechamentocompra.RetornoPagSeguroRequest;
import br.com.zup.desafio.mercadolivre.fechamentocompra.RetornoPagamentoController;
import br.com.zup.desafio.mercadolivre.fechamentocompra.StatusRetornoPagseguro;
import br.com.zup.desafio.mercadolivre.produtos.NovaCaracteristicaRequest;
import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class EventosNovaCompraTest {

	@Autowired
	// Injeta Todos os Mocks que eu fizer*
	@InjectMocks
	private EventosNovaCompra compra;

	@Mock
	private EventoCompraSucesso eventoCompra;

	@Test
	@DisplayName("A compra*")
	void deveriaProcessarComSucesso() {

		Categoria categoria = new Categoria("MinhaCategoria");

		Usuario dono = new Usuario("a@gmail.com", "123456");
		
		NovaCaracteristicaRequest novaCaracteristica = new NovaCaracteristicaRequest("NOME", "Desc");
		NovaCaracteristicaRequest novaCaracteristica2 = new NovaCaracteristicaRequest("Sus", "Desc");
		NovaCaracteristicaRequest novaCaracteristica3 = new NovaCaracteristicaRequest("Desc", "Desc");
		NovaCaracteristicaRequest novaCaracteristica4 = new NovaCaracteristicaRequest("2Desc", "Desc");

		Produto produtoASerComprado = new Produto("Nome", 27, "Descrição!", BigDecimal.ONE, categoria, dono,
				Arrays.asList(novaCaracteristica, novaCaracteristica2, novaCaracteristica3, novaCaracteristica4));
		
		Compra compra = new Compra(produtoASerComprado, 80, dono, GatewayPagamento.PAGSEGURO);
		System.out.println(compra);
		
		RetornoPagSeguroRequest request = new RetornoPagSeguroRequest("O", StatusRetornoPagseguro.SUCESSO);
		
		//controller.processa(compra.getId(), request);

	}

}
