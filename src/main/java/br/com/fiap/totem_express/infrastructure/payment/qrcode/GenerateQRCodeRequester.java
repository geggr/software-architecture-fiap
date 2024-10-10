package br.com.fiap.totem_express.infrastructure.payment;

import br.com.fiap.totem_express.application.payment.QRCodeGateway;
import br.com.fiap.totem_express.domain.order.Order;
import br.com.fiap.totem_express.domain.order.OrderItem;
import br.com.fiap.totem_express.domain.payment.Payment;
import br.com.fiap.totem_express.domain.product.Product;
import br.com.fiap.totem_express.presentation.payment.request.QRCodeItem;
import br.com.fiap.totem_express.presentation.payment.request.QRCodeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.POST;

public class GenerateQRCodeRequester implements QRCodeGateway {

    private final RestTemplate restTemplate;

    @Value("TEST-4165038611844892-100321-b335f4d595822bf85e964dbc6e3e1a6e-793701474")
    private String API_TOKEN;

    @Value("https://api.mercadopago.com/instore/orders/qr/seller/collectors/793701474/pos/SUC001POS001/qrs")
    private String API_URL;

    public GenerateQRCodeRequester(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment generateQRCode(Order order) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.API_TOKEN);


        RequestEntity<QRCodeRequest> request = new RequestEntity<>(
                new QRCodeRequest(
                        order.getPayment().getTransactionId(),
                        UUID.randomUUID().toString(),
                        order.getItems().stream().map(OrderItem::getProduct).map(Product::getDescription).toString(),
                        order.getPayment().getAmount(),
                        List.of(new QRCodeItem(
                                UUID.randomUUID().toString(),
                                order.getPayment().getStatus().toString(),
                                order.getItems().stream().map(OrderItem::getProductName).toString(),
                                order.getItems().stream().map(OrderItem::getProduct).map(Product::getDescription).toString(),
                                order.getItems().stream().map(OrderItem::getTotal).collect(Collectors.toSet()).iterator().next(),
                                order.getItems().stream().map(OrderItem::getQuantity).iterator().next(),
                                "unit",
                                order.getPayment().getAmount()
                        ))), headers, POST, URI.create(API_URL));

        try {
            ResponseEntity<HashMap> response = this.restTemplate.exchange(request, HashMap.class);

            System.out.println("Get Status Code!!" + response.getStatusCode());
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RestClientException("generating QRCode from provider error" + response.getBody());
            }

            return null;


        } catch (Exception e) {
            throw new RestClientException("generating QRCode from provider error");
        }

    }
//
//    private RequestEntity<QRCodeRequest> buildRequest(String transactionId, BigDecimal amount) {
//
//    }

}
