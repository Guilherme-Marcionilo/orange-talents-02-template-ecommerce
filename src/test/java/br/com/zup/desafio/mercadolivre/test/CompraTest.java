package br.com.zup.desafio.mercadolivre.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class CompraTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Deveria retornar ")
	@WithMockUser("e@gmail.com")
	public void deveria () throws Exception{
		
		String request = "{\r\n"
				+ "    \"idTransacao\":1,\r\n"
				+ "    \"status\":1\r\n"
				+ "}";
		
	      mockMvc.perform(MockMvcRequestBuilders.post("/retorno-pagseguro/1")
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(request)
	        ).andExpect(MockMvcResultMatchers.status().is(200))
	                .andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	@DisplayName("Deveria retornar ")
	@WithMockUser("e@gmail.com")
	public void deveriaRetornar() throws Exception{
		
		String request = "{\r\n"
				+ "    \"idTransacao\":134,\r\n"
				+ "    \"status\":ERRO\r\n"
				+ "}";
		
	      mockMvc.perform(MockMvcRequestBuilders.post("/retorno-pagseguro/24")
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(request)
	        ).andExpect(MockMvcResultMatchers.status().is(200))
	                .andDo(MockMvcResultHandlers.print());
		
	}

}
