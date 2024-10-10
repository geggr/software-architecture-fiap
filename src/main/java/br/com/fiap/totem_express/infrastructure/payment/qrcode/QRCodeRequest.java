package br.com.fiap.totem_express.presentation.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record QRCodeRequest(@JsonProperty("external_reference") String transactionId,
                            @JsonProperty("title") String nameOrder,
                            @JsonProperty("description") String description,
                            @JsonProperty("total_amount") BigDecimal amount,
                            @JsonProperty("items") List<QRCodeItem> items) {
}
