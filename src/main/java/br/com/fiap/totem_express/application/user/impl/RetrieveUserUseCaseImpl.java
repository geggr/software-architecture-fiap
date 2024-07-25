package br.com.fiap.totem_express.application.user.impl;

import br.com.fiap.totem_express.application.user.RetrieveUserUseCase;
import br.com.fiap.totem_express.application.user.UserGateway;
import br.com.fiap.totem_express.application.user.output.DefaultUserView;

import java.util.List;

public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserGateway gateway;

    public RetrieveUserUseCaseImpl(UserGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<DefaultUserView> execute() {
        return gateway
                .findAll()
                .stream()
                .map(it -> new DefaultUserView(it.getId(), it.getName()))
                .toList();
    }
}
