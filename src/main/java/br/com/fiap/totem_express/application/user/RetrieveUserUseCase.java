package br.com.fiap.totem_express.application.user;

import br.com.fiap.totem_express.application.user.output.CreatedUserView;

import java.util.List;

public interface RetrieveUserUseCase {
    List<CreatedUserView> execute();
}
