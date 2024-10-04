package br.com.fiap.totem_express.presentation.payment.request;

import java.math.BigDecimal;

public record QRCodeRequest(Long transactionId, BigDecimal amount) {
}
