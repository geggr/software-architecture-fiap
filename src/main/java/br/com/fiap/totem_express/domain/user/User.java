package br.com.fiap.totem_express.domain.user;

import java.time.LocalDateTime;

public class User {

    private String name;
    private String email;
    private String cpf;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Role role;
    private Long id;

    //TODO: Colocar validação
    public User(String name, String email, String cpf) {
        this(name, email, cpf, Role.USER);
    }

    public User(String name, String email, String cpf, Role role) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
    }

    public User(Long id, String name, String email, String cpf, LocalDateTime createdAt, Role role) {
        this(name, email, cpf, role);
        this.id = id;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Role getRole() {
        return role;
    }

}