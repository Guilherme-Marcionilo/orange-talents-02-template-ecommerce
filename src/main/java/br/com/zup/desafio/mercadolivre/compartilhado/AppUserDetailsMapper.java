package br.com.zup.desafio.mercadolivre.compartilhado;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.desafio.mercadolivre.seguranca.UserDetailsMapper;
import br.com.zup.desafio.mercadolivre.usuario.Usuario;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper {

    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario) shouldBeASystemUser);
    }

}
