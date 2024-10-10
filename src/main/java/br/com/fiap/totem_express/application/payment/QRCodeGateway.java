package br.com.fiap.totem_express.application.payment;

import br.com.fiap.totem_express.domain.order.Order;
import br.com.fiap.totem_express.infrastructure.payment.qrcode.QRCodeResponse;

public interface QRCodeGateway {
    QRCodeResponse generateQRCode(Order order);
}
