package br.com.fiap.totem_express.application.user;

import br.com.fiap.totem_express.application.user.output.DefaultUserView;

import java.util.List;

public interface RetrieveUserUseCase {
    List<DefaultUserView> execute();
}
