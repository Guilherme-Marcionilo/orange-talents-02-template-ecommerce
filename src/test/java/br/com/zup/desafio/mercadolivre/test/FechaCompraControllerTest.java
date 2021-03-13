package br.com.zup.desafio.mercadolivre.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.produtos.NovaCaracteristicaRequest;
import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.SenhaLimpa;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;


class FechaCompraControllerTest {

	@DisplayName("Não aceita abater")
	@ParameterizedTest
	@CsvSource({"0","-1","-100"})
	void testPostNovaCompra(int estoque) throws Exception{
		List<NovaCaracteristicaRequest> caracteristicas = List.of(
				new NovaCaracteristicaRequest("Key1", "value1"),
				new NovaCaracteristicaRequest("Key2", "value2"),
				new NovaCaracteristicaRequest("Key3", "value3"));
		
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("a@gmail.com","123456");
		Produto produto = new Produto("nome",10,   "descricao",new BigDecimal(10), categoria, dono, caracteristicas );
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> produto.abataEstoque(estoque));
	}
	
	@DisplayName("Verefica estoque")
	@ParameterizedTest
	@CsvSource({"1,1,true","-1,2,false","4,2,true"})
	void testPostNovaCompra2(int estoque, int quantidadePedida, boolean resultadoEsperado) throws Exception{
		List<NovaCaracteristicaRequest> caracteristicas = List.of(
				new NovaCaracteristicaRequest("Key1", "value1"),
				new NovaCaracteristicaRequest("Key2", "value2"),
				new NovaCaracteristicaRequest("Key3", "value3"));
		
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("a@gmail.com","123456");
		Produto produto = new Produto("nome",estoque,   "descricao",new BigDecimal(10), categoria, dono, caracteristicas );
		boolean resultado = produto.abataEstoque(quantidadePedida);
		
		Assertions.assertEquals(resultadoEsperado, resultado);
	}

}
