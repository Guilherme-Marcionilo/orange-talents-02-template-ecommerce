package br.com.zup.desafio.mercadolivre.produtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

public class NovasImagensRequest {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public List<MultipartFile> getImagens() {
		return imagens;
	}
	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	
}
