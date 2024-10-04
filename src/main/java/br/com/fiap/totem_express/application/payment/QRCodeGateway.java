package br.com.fiap.totem_express.application.payment;

import br.com.fiap.totem_express.domain.payment.Payment;

import java.math.BigDecimal;

public interface QRCodeGateway {
    Payment generateQRCode(Long transactionId, BigDecimal amount);
}
