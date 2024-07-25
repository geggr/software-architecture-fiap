package br.com.fiap.totem_express.presentation.user;

import br.com.fiap.totem_express.application.user.CreateUserUseCase;
import br.com.fiap.totem_express.application.user.RetrieveUserUseCase;
import br.com.fiap.totem_express.application.user.UserGateway;
import br.com.fiap.totem_express.presentation.user.requests.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserDocumentation {

    private final RetrieveUserUseCase retrieveUserUseCase;
    private final CreateUserUseCase createUserUseCase;

    public UserController(RetrieveUserUseCase retrieveUserUseCase, CreateUserUseCase createUserUseCase) {
        this.retrieveUserUseCase = retrieveUserUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateUserRequest request){
        final var view = createUserUseCase.execute(request);

        return ResponseEntity.ok(view);
    }

    @GetMapping
    public ResponseEntity find(){
        final var items = retrieveUserUseCase.execute();
        return ResponseEntity.ok(items);
    }
}
