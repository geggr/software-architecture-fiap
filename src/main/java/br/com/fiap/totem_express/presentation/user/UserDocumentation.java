package br.com.fiap.totem_express.presentation.user;

import br.com.fiap.totem_express.application.user.input.NewUserInput;
import br.com.fiap.totem_express.presentation.user.requests.CreateUserRequest;
import org.springframework.http.ResponseEntity;

public interface UserDocumentation {
    ResponseEntity create(CreateUserRequest request);
}
