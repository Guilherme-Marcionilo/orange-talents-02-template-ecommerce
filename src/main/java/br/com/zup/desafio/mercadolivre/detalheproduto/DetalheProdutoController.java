package br.com.zup.desafio.mercadolivre.detalheproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.produtos.Produto;

@RestController
public class DetalheProdutoController {
	
	@PersistenceContext
	private EntityManager em;

	
	@GetMapping(value = "/produtos/{id}")
	public DetalheProdutoView detalhar(@PathVariable("id") Long id) {
		
		Produto produtoEscolhido = em.find(Produto.class, id);
		
		return new DetalheProdutoView(produtoEscolhido);
	}
	
}
