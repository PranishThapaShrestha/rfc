package com.nicasia.rfc.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class Userprincipal implements UserDetails {

    private final User user;
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public Userprincipal(User user, Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static Userprincipal build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(roles ->
                new SimpleGrantedAuthority(roles.getName().name())).collect(Collectors.toList());

        return new Userprincipal(
                user,
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userprincipal that = (Userprincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
