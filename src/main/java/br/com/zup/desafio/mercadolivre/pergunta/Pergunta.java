package br.com.zup.desafio.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.zup.desafio.mercadolivre.produtos.Produto;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta implements Comparable<Pergunta>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String titulo;

	@ManyToOne
	private @Valid Usuario interessada;

	@ManyToOne
	private @Valid Produto produto;
	
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Pergunta() {}
	
	public Pergunta(@NotBlank String titulo, @Valid Usuario interessada, @Valid Produto produto) {
		this.titulo = titulo;
		this.interessada = interessada;
		this.produto = produto;
	}
	
	public LocalDateTime getInstante() {
		return instante;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getInteressada() {
		return interessada;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instante == null) ? 0 : instante.hashCode());
		result = prime * result + ((interessada == null) ? 0 : interessada.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instante == null) {
			if (other.instante != null)
				return false;
		} else if (!instante.equals(other.instante))
			return false;
		if (interessada == null) {
			if (other.interessada != null)
				return false;
		} else if (!interessada.equals(other.interessada))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Pergunta o) {
		return this.titulo.compareTo(o.titulo);
	}
	
	

}
