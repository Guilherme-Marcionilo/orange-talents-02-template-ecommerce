package br.com.zup.desafio.mercadolivre.categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table (name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@ManyToOne	 
	@NotNull
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

	public void setMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
		
	}

}
