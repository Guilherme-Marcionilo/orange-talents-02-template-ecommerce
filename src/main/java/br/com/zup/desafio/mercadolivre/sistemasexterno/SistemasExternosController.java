package br.com.zup.desafio.mercadolivre.sistemasexterno;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.usuario.Usuario;

@RestController
public class SistemasExternosController {

    @PostMapping("/notas-fiscais")
    public void criaNota(@Valid @RequestBody NotaFiscalRequest notaFiscalRequest, @AuthenticationPrincipal Usuario comprador) throws InterruptedException {
        System.out.println("criando nota " + notaFiscalRequest);
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody RankingRequest rankingRequest, @AuthenticationPrincipal Usuario comprador) throws InterruptedException {
        System.out.println("criando ranking" + rankingRequest);
        Thread.sleep(150);
    }
}
