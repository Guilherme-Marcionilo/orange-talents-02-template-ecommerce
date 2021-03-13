package br.com.zup.desafio.mercadolivre.usuario;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(@Email String email);
}
