package br.com.fiap.totem_express.application.user;

import br.com.fiap.totem_express.application.user.input.NewUserInput;
import br.com.fiap.totem_express.application.user.output.CreatedUserView;

public interface CreateUserUseCase {
    CreatedUserView execute(NewUserInput input);
}
