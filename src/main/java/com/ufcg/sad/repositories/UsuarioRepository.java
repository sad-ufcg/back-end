package com.ufcg.sad.repositories;

import com.ufcg.sad.models.usuario.Usuario;
import com.ufcg.sad.security.Credenciais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface que provê serviços de consulta ao Banco de Dados
 * para a entidade Usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT new com.ufcg.sad.security.Credenciais(u.email, u.senha) from Usuario u where u.email = ?1")
    Credenciais getCredenciais(String email);
}
