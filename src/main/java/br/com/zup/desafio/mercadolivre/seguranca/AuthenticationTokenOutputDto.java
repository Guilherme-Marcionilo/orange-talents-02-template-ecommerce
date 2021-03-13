package br.com.zup.desafio.mercadolivre.seguranca;

public class AuthenticationTokenOutputDto {

	private String tokenType;
	private String token;

	public AuthenticationTokenOutputDto(String tokenType, String token) {
		super();
		this.tokenType = tokenType;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getTokenType() {
		return tokenType;
	}

}