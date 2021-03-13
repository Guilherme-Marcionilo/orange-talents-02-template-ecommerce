package br.com.zup.desafio.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.zup.desafio.mercadolivre.compartilhado.ExistsId;
import br.com.zup.desafio.mercadolivre.compartilhado.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank(message = "Ops! O nome está vazio (nulo)!")
	@Length(max = 50)
	@UniqueValue(domainClass = Categoria.class, message = "Ops! O nome da Categoria é único!", fieldName = "nome")
	private String nome;

	@Positive(message = "Ops! O número deve ser maior que 0!")
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	@Deprecated
	public NovaCategoriaRequest() {
	}

	public NovaCategoriaRequest(@NotBlank(message = "Ops! O nome está vazio (nulo)!") @Length(max = 50) String nome,
			@Positive(message = "Ops! O número deve ser maior que 0!") Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}



	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria toModel(EntityManager em) {
		
		Categoria categoria = new Categoria(nome);
		
		if(idCategoriaMae != null) {
			Categoria categoriaMae = em.find(Categoria.class, idCategoriaMae);
			//Assert.notNull(categoriaMae, "O ID da Categoria Mãe precisa ser válido!");
			categoria.setMae(categoriaMae);
		}
		
		return categoria;
	}
	
}
