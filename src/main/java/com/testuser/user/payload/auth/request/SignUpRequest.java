package com.testuser.user.payload.auth.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max=20)
    private String username;

    @NotBlank
    @Size(min = 6, max=40)
    private String password;

    @NotBlank
    @Size(max=50)
    private String nama;

    @NotBlank
    private String createdAt;

    @NotBlank
    private  String createdBy;

    private String foto;

    private Set<String> role;

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nama='" + nama + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", foto='" + foto + '\'' +
                ", role=" + role +
                '}';
    }
}
