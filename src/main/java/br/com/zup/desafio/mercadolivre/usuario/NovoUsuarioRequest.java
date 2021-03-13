package br.com.zup.desafio.mercadolivre.usuario;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zup.desafio.mercadolivre.compartilhado.UniqueValue;

public class NovoUsuarioRequest {

	@NotBlank
	@Email(message = "O email está inválido! Deve conter @ e .com")
	@UniqueValue (domainClass = Usuario.class, message = "Ops! Este Email já existe!", fieldName = "email")
	private String email;

	@NotBlank
	@Length(min = 6, max = 50, message = "Ops! A senha está inválida!")
	private String senha;

	private LocalDate dataCriacao = LocalDate.now();

	@Deprecated
	public NovoUsuarioRequest() {
	}

	public NovoUsuarioRequest(@NotBlank @Email(message = "O email está inválido! Deve conter @ e .com") String email,
			@NotBlank @Length(min = 6, max = 50, message = "Ops! A senha está inválida!") String senha) {
		this.email = email;
		this.senha = senha;
	}



	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario toModel() {
		return new Usuario(this.email, this.senha);
	}



}
