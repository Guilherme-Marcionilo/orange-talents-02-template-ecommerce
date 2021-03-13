package br.com.zup.desafio.mercadolivre.usuario;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * Representa uma senha limpa no sistema
 * @author guilherme.silva
 */

public class SenhaLimpa {

	@NotBlank @Length(min = 6)
	private String senha;
	
	public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
		
		Assert.hasLength(senha, "Ops! A Senha não pode estar em branco");
		Assert.isTrue(senha.length() >= 6, "Ops! A Senha deve conter no mínimo 6 caracteres!");
		this.senha = senha; 
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
}
