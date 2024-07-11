package br.com.fiap.totem_express.domain.user;

import java.time.LocalDateTime;

public class User {
    private String name;
    private String email;
    private String cpf;
    private LocalDateTime createdAt  = LocalDateTime.now();
    private Role role;
    private Long id;

    //TODO: Colocar validação
    public User(String name, String email, String cpf) {
        this(name, email,  cpf, Role.USER);
    }

    public User(String name, String email, String cpf, Role role) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
    }

}
