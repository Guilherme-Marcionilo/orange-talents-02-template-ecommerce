package br.com.zup.desafio.mercadolivre.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.categoria.NovaCategoriaRequest;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
@ExtendWith(MockitoExtension.class)
public class CategoriaControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@PersistenceContext
	EntityManager em;

	String urlHost = "http://localhost:8080";

	@Test
	@DisplayName("Deveria criar uma nova categoira e retornar 200")
	@WithMockUser(username = "a@gmail.com", password = "123456")
	void deveriaCriarNovaCategoria() throws JsonProcessingException, Exception {

		NovaCategoriaRequest novaCat = new NovaCategoriaRequest("Categoria Teste", Long.valueOf(1));

		mockMvc.perform(MockMvcRequestBuilders.post(urlHost + "/categorias").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(novaCat)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Deveria tentar criar uma nova categoria com o nome nulo")
	void deveriaCriarNovaCategoriaComNomeNulo() throws JsonProcessingException, Exception {

		NovaCategoriaRequest newCategoryFormIn = new NovaCategoriaRequest("Nome",Long.valueOf(1));

		mockMvc.perform(MockMvcRequestBuilders.post(urlHost + "/categorias").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newCategoryFormIn)))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	@DisplayName("Deveria criar uma nova categoria com uma subcategoria (categoria Mãe)")
	@WithMockUser(username = "a@gmail.com", password = "123456", roles = "MODERATOR")
	void deveriaCriaUmaCategoriaComSubCategoria() throws JsonProcessingException, Exception {

		NovaCategoriaRequest newCategoryFormIn = new NovaCategoriaRequest("Categoria", null);

		mockMvc.perform(MockMvcRequestBuilders.post(urlHost + "/categorias").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newCategoryFormIn)))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	@DisplayName("Deveria tentar criar uma nova categoria, porém, com usuário NÃO AUTORIZADO!")
	@WithMockUser(username = "a@gmail.com", password = "123456", roles = "USER")
	void deveriaTentarCriarCategoriaSemAutenticacao() throws JsonProcessingException, Exception {

		NovaCategoriaRequest newCategoryFormIn = new NovaCategoriaRequest("Category", Long.valueOf(1));

		mockMvc.perform(MockMvcRequestBuilders.post(urlHost + "/categorias").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newCategoryFormIn)))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	@DisplayName("Deveria criar uma categoria sem nome nulo!")
	void deveriaCriarCategoriaComNomeVazio() throws JsonProcessingException, Exception {

		NovaCategoriaRequest newCategoryFormIn = new NovaCategoriaRequest("",Long.valueOf(1));

		mockMvc.perform(MockMvcRequestBuilders.post(urlHost + "/categorias").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newCategoryFormIn)))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	@DisplayName("Deveria tentar criar uma nova categoria, testando se está com nomes duplicados")
	void deveriaCriarComNomesDuplicados() throws JsonProcessingException, Exception {

		Categoria categoria = new Categoria("Esportes");
		
		NovaCategoriaRequest novaCategoria = new NovaCategoriaRequest("Category",Long.valueOf(1));

		em.persist(categoria);
		
		em.persist(novaCategoria.toModel(em));

		List<Categoria> categorias = em
				.createQuery("select c from Categoria c where c.name = :nome", Categoria.class)
				.setParameter("catName", novaCategoria.getNome())
				.getResultList();

		assertTrue(categorias.size() == 1);

		mockMvc.perform(MockMvcRequestBuilders.post(urlHost + "/categorias").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(novaCategoria)))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

}
