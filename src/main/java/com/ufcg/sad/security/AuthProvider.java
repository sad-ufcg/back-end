package com.ufcg.sad.security;

import com.ufcg.sad.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String email = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Credenciais credenciais = usuarioRepository.getCredenciais(email);

        if (credenciais != null && email.equals(credenciais.getLogin()) && senha.equals(credenciais.getSenha())) {
            return new UsernamePasswordAuthenticationToken(email, senha, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
