package br.com.fiap.totem_express.presentation.user;

import br.com.fiap.totem_express.application.user.input.NewUserInput;
import br.com.fiap.totem_express.application.user.output.DefaultUserView;
import br.com.fiap.totem_express.presentation.errors.BadRequestError;
import br.com.fiap.totem_express.presentation.user.requests.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuarios", description = "API de Usuários")
public interface UserDocumentation {
    @Operation(summary = "Cria novo usuário", description = "Retorna o novo usuário cadastrado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso ao cadastrar o usuário",
                    content = { @Content(schema = @Schema(implementation = DefaultUserView.class),mediaType = "application/json")
            }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Quando há erro de validação",
                    content = {@Content(schema = @Schema(implementation = BadRequestError.class))}
            )
    })
    ResponseEntity<?> create(CreateUserRequest request);

    @Operation(summary = "Lista usuários",description = "Retorna os usuários cadastrados no resturante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DefaultUserView.class), mediaType = "application/json"))
    })
    ResponseEntity<?> find(String document);
}
