package br.com.zup.desafio.mercadolivre.detalheproduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import br.com.zup.desafio.mercadolivre.produtos.Opinioes;
import br.com.zup.desafio.mercadolivre.produtos.Produto;

public class DetalheProdutoView {

	private String descricao;
	private String nome;
	private BigDecimal valor;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String,String>> opinioes;
	private double mediaNotas;
	private int total;

	@Deprecated
	public DetalheProdutoView() {}
	
	public DetalheProdutoView(Produto produto) {
		
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
		
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
		
		Opinioes opinioes = produto.getOpinioes();
		
		this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		this.mediaNotas = opinioes.media();
		this.total = opinioes.totalOpinioes();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}

	public int getTotal() {
		return total;
	}
	
	
}
