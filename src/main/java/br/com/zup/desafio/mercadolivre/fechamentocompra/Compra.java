package br.com.zup.desafio.mercadolivre.fechamentocompra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import com.sun.istack.NotNull;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produtoEscolhido;
	@Positive
	private int quantidade;
	@ManyToOne
	@NotNull
	@Valid
	private Usuario comprador;

	@Enumerated
	@NotNull
	private GatewayPagamento gatewayPagamento;
	
	@OneToMany(mappedBy ="compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	@Deprecated
	public Compra() {
	}

	public Compra(@NotNull @Valid Produto produtoASerComprado, @Positive int quantidade,
			@NotNull @Valid Usuario comprador, GatewayPagamento gatewayPagamento) {
		this.produtoEscolhido = produtoASerComprado;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public Long getId() {
		return id;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public Set<Transacao> getTransacoes() {
		return transacoes;
	}
	
	public Usuario getDonoProduto() {
		return produtoEscolhido.getDono();
	}
	
	public String urlRedirecionamento(
			UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
	}
	
	public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
		Transacao novaTransacao = request.toTransacao(this);
		
		Assert.state(!this.transacoes.contains(novaTransacao),
				"Já existe uma transacao igual a essa processada "
						+ novaTransacao);
		Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Essa compra já foi concluída com sucesso");

		this.transacoes.add(novaTransacao);
	}
	
	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
				.filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Ops! Ocorreu um problema, tem mais de uma transacao concluida com sucesso aqui na compra "+this.id);
		
		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produtoEscolhido=" + produtoEscolhido + ", quantidade=" + quantidade
				+ ", comprador=" + comprador + ", gatewayPagamento=" + gatewayPagamento + ", transacoes=" + transacoes
				+ "]";
	}

}
