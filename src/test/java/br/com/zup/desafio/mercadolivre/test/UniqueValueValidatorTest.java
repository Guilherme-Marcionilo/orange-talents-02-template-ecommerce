package br.com.zup.desafio.mercadolivre.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.zup.desafio.mercadolivre.usuario.Usuario;

public class UniqueValueValidatorTest {

    @Mock
    EntityManager em = Mockito.mock(EntityManager.class);

    @InjectMocks
    Usuario usuario = new Usuario("email@email.com", "123456");

    @Mock
    Query query = Mockito.mock(Query.class);

    @Test
    @DisplayName("Deve retornar que já tem uma cadastrado")
    void isValid1() {

        em.persist(usuario);

        String jpql = "SELECT 1 FROM Usuario WHERE email =:value";

        List<Usuario> list = new ArrayList<>();

        Mockito.when(em.createQuery(jpql)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(list);
        Mockito.when(query.setParameter("value", usuario.getEmail())).thenReturn(query);

        Assertions.assertFalse(list.size() == 1, "Tem mais de uma informação ou nenhuma");

    }

    @Test
    @DisplayName("Deve retornar que não tem nenhum cadastrado")
    void isValid2() {

        em.persist(usuario);

        String jpql = "SELECT 1 FROM Usuario WHERE email =:value";

        List<Usuario> list = new ArrayList<>();

        Mockito.when(em.createQuery(jpql)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(list);
        Mockito.when(query.setParameter("value", usuario.getEmail())).thenReturn(query);

        Assertions.assertTrue(list.size() == 0, "Já está cadastrado");

    }

    @Test
    @DisplayName("Deve retornar que tem mais de um cadastrado")
    void isValid3() {
        em.persist(usuario);

        String jpql = "SELECT 1 FROM Usuario WHERE email =:value";

        List<Usuario> list = new ArrayList<>();

        Mockito.when(em.createQuery(jpql)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(list);
        Mockito.when(query.setParameter("value", usuario.getEmail())).thenReturn(query);

        Assertions.assertFalse(list.size() > 1, "Tem mais de um cadastrado");

    }

}
