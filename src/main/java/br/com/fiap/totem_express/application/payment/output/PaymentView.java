package br.com.fiap.totem_express.application.payment.output;

import br.com.fiap.totem_express.domain.payment.Status;

public interface PaymentView {

    Long id();

    Status status();

    record SimpleView(Long id, Status status) implements PaymentView {
    }
}