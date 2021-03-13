package br.com.zup.desafio.mercadolivre.pergunta;

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
public class PerguntaController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Emails emails;

	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id) {

		Produto produto = em.find(Produto.class, id);

		Usuario interessada = usuarioRepository.findByEmail("a@gmail.com").get();

		Pergunta novaPergunta = request.toModel(interessada, produto);
		
		emails.novaPergunta(novaPergunta);
		
		

		em.persist(novaPergunta);
		return ResponseEntity.ok(novaPergunta);
	}
}
