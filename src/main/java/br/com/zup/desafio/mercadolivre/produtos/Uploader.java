package br.com.zup.desafio.mercadolivre.produtos;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader{

	Set<String> envia(List<MultipartFile> imagens);



}
