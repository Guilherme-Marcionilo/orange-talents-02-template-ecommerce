package br.com.zup.desafio.mercadolivre.test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.zup.desafio.mercadolivre.produtos.NovaCaracteristicaRequest;
import br.com.zup.desafio.mercadolivre.produtos.NovoProdutoRequest;

public class NovoProdutoRequestTest {

    static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(0,
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac2", "blablabla"),
                                new NovaCaracteristicaRequest("carac3", "blablabla"))),

                Arguments.of(1,
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac2", "blablabla"))),

                Arguments.of(1,
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac1", "blablabla")))
        );
    }

    @ParameterizedTest
    @DisplayName("Deveria retornar se tem características com nomes iguais")
    @MethodSource("geradorTeste1")
    void testeNovoProduto1(int esperado, List<NovaCaracteristicaRequest> caracteristicas) throws Exception {

        NovoProdutoRequest novoProdutoRequest = new NovoProdutoRequest("outro nome", 10, "descricao", BigDecimal.TEN,  1L,  caracteristicas);

        Assertions.assertEquals(esperado, novoProdutoRequest.buscaCaracteristicaIguais().size());
    }
}
