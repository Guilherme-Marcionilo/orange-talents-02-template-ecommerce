package br.com.zup.desafio.mercadolivre.fechamentocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.mercadolivre.pergunta.Emails;
import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;
import br.com.zup.desafio.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/compras")
public class FechamentoCompraController {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Emails emails;

	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {

		Produto produtoASerComprado = em.find(Produto.class, request.getIdProduto());

		int quantidade = request.getQuantidade();
		boolean abateu = produtoASerComprado.abataEstoque(quantidade);
	

		if (abateu) {
			Usuario comprador = usuarioRepository.findByEmail("a@gmail.com").get();

			GatewayPagamento gateway = request.getGateway();

			Compra novaCompra = new Compra(produtoASerComprado, quantidade, comprador, gateway);
			em.persist(novaCompra);

			emails.novaCompra(novaCompra);
			
			return novaCompra.urlRedirecionamento(uriComponentsBuilder);

		}

		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;

	}

}
