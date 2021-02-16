package com.testuser.user.payload.auth.respone;

import java.util.List;

public class JwtRespones {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String nama;
    private String foto;
    private String createdAt;
    private List<String> roles;


    public JwtRespones(String token, Long id, String username, String nama, String foto,String createdAt,List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.nama = nama;
        this.foto = foto;
        this.createdAt = createdAt;
        this.roles = roles;
    }

    public JwtRespones() {
    }


    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
