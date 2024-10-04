package br.com.fiap.totem_express.infrastructure.payment;

import br.com.fiap.totem_express.application.payment.QRCodeGateway;
import br.com.fiap.totem_express.domain.payment.Payment;
import br.com.fiap.totem_express.presentation.payment.request.QRCodeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.http.HttpMethod.POST;

public class GenerateQRCodeRequester implements QRCodeGateway {

    private final RestTemplate restTemplate;

    @Value("APP_USR-4165038611844892-100321-152c6d143690fe7786cb862fe6fa326d-793701474")
    private String API_TOKEN;

    @Value("https://api.mercadopago.com/instore/orders/qr/seller/collectors/{user_id}/pos/{external_pos_id}/qrs")
    private String API_URL;

    public GenerateQRCodeRequester(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment generateQRCode(Long transactionId, BigDecimal amount) {
        RequestEntity<QRCodeRequest> request = buildRequest(transactionId, amount);

        try {
            ResponseEntity<Payment> response = this.restTemplate.exchange(request, Payment.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RestClientException("generating QRCode from provider error");
            }

            return response.getBody();


        } catch (Exception e) {
            throw new RestClientException("generating QRCode from provider error");
        }

    }

    private RequestEntity<QRCodeRequest> buildRequest(Long referenceId, BigDecimal amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.API_TOKEN);
        return new RequestEntity<>(new QRCodeRequest(referenceId, amount), headers, POST, URI.create(API_URL));
    }

}
