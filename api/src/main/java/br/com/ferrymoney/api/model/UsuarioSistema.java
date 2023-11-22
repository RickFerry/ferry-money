package br.com.ferrymoney.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsuarioSistema extends User {
    private final Usuario usuario;

    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(),  usuario.getSenha(), authorities);
        this.usuario = usuario;
    }
}
