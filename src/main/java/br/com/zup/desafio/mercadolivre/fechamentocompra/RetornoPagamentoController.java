package br.com.zup.desafio.mercadolivre.fechamentocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetornoPagamentoController {

	@PersistenceContext
    private EntityManager em;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;

	@PostMapping("/retorno-pagseguro/{id}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPagSeguroRequest request) {
		return processa(idCompra, request);
	}
	
	@PostMapping("/retorno-paypal/{id}")
	@Transactional
	public String processamentoPaypal(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPaypalRequest request) {
		
		return processa(idCompra, request);
	}
	
	private String processa(Long idCompra,RetornoGatewayPagamento retornoGatewayPagamento) {
		Compra compra = em.find(Compra.class, idCompra);
		compra.adicionaTransacao(retornoGatewayPagamento);		
		em.merge(compra);		
		eventosNovaCompra.processa(compra);
		
		return compra.toString();		
	}
	
}
