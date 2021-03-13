package br.com.zup.desafio.mercadolivre.opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;
import br.com.zup.desafio.mercadolivre.usuario.UsuarioRepository;

@RestController
public class OpiniaoController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "/produtos/{id}/opiniao")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaOpiniaoRequest request,
			@PathVariable("id") Long id) {
		
		
		Produto	produto = em.find(Produto.class, id);
		
		Usuario consumidor = usuarioRepository.findByEmail("a@gmail.com").get();

		Opiniao novaOpiniao = request.toModel(produto,consumidor);
		
		em.persist(novaOpiniao);
		
		return ResponseEntity.ok(novaOpiniao);
	}
	
}
