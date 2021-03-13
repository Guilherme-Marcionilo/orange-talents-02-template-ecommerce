package br.com.zup.desafio.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid NovaCategoriaRequest request) {
		
		Categoria novaCategoria = request.toModel(em);
		
		em.persist(novaCategoria);
		return ResponseEntity.ok().build();
	}
	
}
