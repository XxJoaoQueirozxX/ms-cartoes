package com.ms.cartoes.msavaliadorcredito.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCard {
    private String name;
    private String brand;
    private BigDecimal limit;
}
