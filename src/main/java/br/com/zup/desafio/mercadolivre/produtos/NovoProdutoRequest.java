package br.com.zup.desafio.mercadolivre.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zup.desafio.mercadolivre.categoria.Categoria;
import br.com.zup.desafio.mercadolivre.compartilhado.ExistsId;
import br.com.zup.desafio.mercadolivre.compartilhado.UniqueValue;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

public class NovoProdutoRequest {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "OPS! Existe nomes repetidos!")
	private String nome;

	@Positive
	@NotNull
	private int quantidade;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	private BigDecimal valor;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id", message = "OPS! Não existe este ID")
	private Long idCategoria;

	@Size(min = 3, max = 60, message = "OPS! O tamanho deve ser entre 3 e 60!")
	@Valid
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

	@Deprecated
	public NovoProdutoRequest() {
	}

	public NovoProdutoRequest(@NotBlank String nome, @Positive int quantidade,
			@NotBlank @Size(max = 1000) String descricao, BigDecimal valor, Long idCategoria,
			@Size(min = 3) @Valid List<NovaCaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}


	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao
				+ ", valor=" + valor + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}
	

	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto toModel(EntityManager em, Usuario dono) {
		Categoria categoria = em.find(Categoria.class, idCategoria);
		return new Produto(this.nome, this.quantidade, this.descricao, this.valor, categoria, dono,caracteristicas);
	}
	
	public Set<String> buscaCaracteristicaIguais() {
		
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
			
			String nome = caracteristica.getNome();
			
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		
		return resultados;
	}

}
