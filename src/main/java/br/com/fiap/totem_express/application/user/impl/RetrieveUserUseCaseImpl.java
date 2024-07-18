package br.com.fiap.totem_express.application.user.impl;

import br.com.fiap.totem_express.application.user.RetrieveUserUseCase;
import br.com.fiap.totem_express.application.user.UserGateway;
import br.com.fiap.totem_express.application.user.output.CreatedUserView;

import java.util.List;

public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserGateway gateway;

    public RetrieveUserUseCaseImpl(UserGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<CreatedUserView> execute() {
        return gateway
                .findAll()
                .stream()
                .map(it -> (CreatedUserView) new CreatedUserView.SimpleCreatedView(it.getId(), it.getName()))
                .toList();
    }
}
