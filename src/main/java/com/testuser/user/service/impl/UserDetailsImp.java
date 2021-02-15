package com.testuser.user.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testuser.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImp implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long Id;
    private String username;
    private  String nama;
    private String createdAt;
    private  String foto;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImp(Long id, String username, String nama, String password,String createdAt,String foto,
                          Collection<? extends GrantedAuthority> authorities) {
        Id = id;
        this.username = username;
        this.nama = nama;
        this.createdAt= createdAt;
        this.foto= foto;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImp build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImp(
                user.getId(),
                user.getUserName(),
                user.getNama(),
                user.getCreatedAt(),
                user.getFoto(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return Id;
    }

    public String getNama() {
        return nama;
    }

    public String getCreatedAt() { return createdAt; }
    public String getFoto() { return foto; }


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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDetailsImp user = (UserDetailsImp) obj;
        return Objects.equals(Id, user.Id);
    }
}
