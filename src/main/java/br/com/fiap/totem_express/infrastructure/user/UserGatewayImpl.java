package br.com.fiap.totem_express.infrastructure.user;

import br.com.fiap.totem_express.application.user.UserGateway;
import br.com.fiap.totem_express.domain.user.User;

import java.util.List;

public class UserGatewayImpl implements UserGateway {
    private final UserRepository repository;

    public UserGatewayImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        final var created = repository.save(new UserEntity(user));
        return created.toDomain();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(UserEntity::toDomain).toList();
    }

    @Override
    public boolean existsByEmailOrCPF(String email, String cpf) {
        return repository.existsByEmailOrCpf(email, cpf);
    }
}
