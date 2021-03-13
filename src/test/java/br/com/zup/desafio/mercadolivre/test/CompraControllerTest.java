package br.com.zup.desafio.mercadolivre.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.fechamentocompra.Compra;
import br.com.zup.desafio.mercadolivre.produtos.NovaCaracteristicaRequest;
import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

public class CompraControllerTest {
	
		@Mock
	    private EntityManager em;

	    private List<NovaCaracteristicaRequest> caracteristicaRequests = List.of(new NovaCaracteristicaRequest("carac1", "alguma coisa"),
	            new NovaCaracteristicaRequest("carac2", "alguma coisa"),
	            new NovaCaracteristicaRequest("carac3", "alguma coisa"));

	    private Categoria categoria = new Categoria("categoria");
	    private Usuario dono = new Usuario("a@gmail.com", "123456");

	    @Test
	    @DisplayName("Redireciona para o gateway quando abate do estoque")
	    void fecharCompra() throws Exception {

	        Produto produto = new Produto("nome", 2, "descricao", BigDecimal.TEN,
	        		categoria, dono,caracteristicaRequests);


	        Mockito.when(em.find(Produto.class, 1l)).thenReturn(produto);


	        Mockito.doAnswer(invocation -> {
	            Compra compra = invocation.<Compra>getArgument(0);

	            ReflectionTestUtils.setField(compra, "id", 1l);
	            return null;
	        }).when(em).persist(Mockito.any(Compra.class));

	    }
}
