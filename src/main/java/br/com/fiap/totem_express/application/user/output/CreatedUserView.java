package br.com.fiap.totem_express.application.user.output;

public interface CreatedUserView {

    Long id();

    String name();

    record SimpleCreatedView(Long id, String name) implements CreatedUserView {

    }
}
