package br.com.zup.desafio.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table (name = "usuarios")
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;

    private LocalDateTime instanteCriacao = LocalDateTime.now();

    public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Deprecated
    public Usuario() {
    }


	public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", instanteCriacao=" + instanteCriacao
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}


    
}