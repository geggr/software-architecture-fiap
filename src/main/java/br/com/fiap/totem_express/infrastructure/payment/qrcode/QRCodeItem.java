package br.com.fiap.totem_express.infrastructure.payment.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record QRCodeItem(@JsonProperty("sku_number") String skuNumber,
                         @JsonProperty("title") String title,
                         @JsonProperty("description") String description,
                         @JsonProperty("unit_price") BigDecimal unitPrice,
                         @JsonProperty("quantity") Long quantity,
                         @JsonProperty("unit_measure") String unitMeasure,
                         @JsonProperty("total_amount") BigDecimal totalAmount) {
}
