package com.example.buensabor.security.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioPrincipal implements UserDetails {

    private String nombre;
    private String apellido;
    private long telefono;
    private String email;
    private String clave;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombre, String apellido, long telefono, String email, String clave, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name()))
                .collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getClave(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
