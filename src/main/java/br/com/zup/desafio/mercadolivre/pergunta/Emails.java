package br.com.zup.desafio.mercadolivre.pergunta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;

import br.com.zup.desafio.mercadolivre.fechamentocompra.Compra;

@Service
public class Emails{

	@Autowired
	private Mailer mailer;
	
	
	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getInteressada().getEmail(),
				"a@gmail.com",pergunta.getDonoProduto().getEmail());
	}


	public void novaCompra(Compra novaCompra) {
		mailer.send("nova compra..." + novaCompra, "Você tem uma nova compra",
				novaCompra.getComprador().getEmail(),
				"a@gmail.com",
				novaCompra.getDonoProduto().getEmail());
	}

}
