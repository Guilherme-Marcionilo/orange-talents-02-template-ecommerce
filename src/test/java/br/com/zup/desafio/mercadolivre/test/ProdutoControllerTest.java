package br.com.zup.desafio.mercadolivre.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.produtos.NovaCaracteristicaRequest;
import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

class ProdutoControllerTest {

    @Mock
    EntityManager em = Mockito.mock(EntityManager.class);

    List<NovaCaracteristicaRequest> caracteristicas = Arrays.asList(new NovaCaracteristicaRequest("1", "desc"),
            new NovaCaracteristicaRequest("2", "desc"),
            new NovaCaracteristicaRequest("3", "desc"));

    @InjectMocks
    Usuario usuario = new Usuario("email@email.com", "123456");

    @InjectMocks
    Categoria categoria = new Categoria("categoria");


    static Stream<Arguments> geradorImagem1() {
        Usuario usuarioLogado1 = new Usuario("email@email.com", "123456");
        Usuario usuarioLogado2 = new Usuario("email2@email.com", "123456");
        return Stream.of(Arguments.of(usuarioLogado1),
                Arguments.of(usuarioLogado2));
    }

    @DisplayName("Deve retornar que o usuário logado é o responsável pelo produto")
    @ParameterizedTest
    @MethodSource("geradorImagem1")
    void adicionarImagem1(Usuario usuarioLogado) {
        em.persist(usuario);
        
        Usuario dono = new Usuario("email@email.com", "123456");

		Produto produtoASerComprado = new Produto("teste", 100, "descricao",
				BigDecimal.TEN, categoria, dono, caracteristicas);

        Mockito.when(!produtoASerComprado.getDono().getEmail().equals(usuarioLogado.getEmail())).
                thenThrow(ResponseStatusException.class);

    }
}
