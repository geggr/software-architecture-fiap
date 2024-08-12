package br.com.fiap.totem_express.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmailOrCpf(String email, String cpf);
    Optional<UserEntity> findByCpf(String cpf);
}
