package br.com.zup.desafio.mercadolivre.usuario;

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
@RequestMapping("/usuario")
public class UsuarioController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoUsuarioRequest request) {
		
		Usuario novoUsuario = request.toModel();
		
		em.persist(novoUsuario);
		
		return ResponseEntity.ok(novoUsuario);
	}
			
}
