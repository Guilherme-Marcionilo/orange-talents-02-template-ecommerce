package br.com.zup.desafio.mercadolivre.produtos;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.desafio.mercadolivre.usuario.Usuario;
import br.com.zup.desafio.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produto")
public class ProdutosController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired	
	private Uploader uploaderFake;
	
	@InitBinder(value = "novoProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar (@RequestBody @Valid NovoProdutoRequest request) {
		
		Usuario dono = usuarioRepository.findByEmail("a@gmail.com").get();
		Produto novoProduto = request.toModel(em, dono);
				
		em.persist(novoProduto);
		return ResponseEntity.ok().build();
	} 
	
	@PostMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest  request){
		/**
		 * 1) Enviar as imagens para o local onde elas ficarão alocadas
		 * 2) Pegar os links de todas as imagens
		 * 3) Associar esses links com o produto em questão
		 * 4) Preciso carregar o produto
		 * 5) Depois que associar eu preciso atualizar a nova versão do produto
		 */
		
		 Usuario dono = usuarioRepository.findByEmail("a@gmail.com").get();	
		 Produto produto = em.find(Produto.class, id);
		 
		 if(!produto.pertenceAoUsuario(dono)) {
			 throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		 }
		 Set<String> links = uploaderFake.envia(request.getImagens());
		 produto.associaImagens(links);
		
		 em.merge(produto);
		
		return ResponseEntity.ok(produto);
	}
	

	
	
}
