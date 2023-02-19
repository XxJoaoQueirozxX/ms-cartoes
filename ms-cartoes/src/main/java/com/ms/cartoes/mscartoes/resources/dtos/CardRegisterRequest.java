package com.ms.cartoes.mscartoes.resources.dtos;

import com.ms.cartoes.mscartoes.entities.Card;
import com.ms.cartoes.mscartoes.entities.enums.CardBrand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardRegisterRequest {

    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal initialLimit;


    public Card toCard() {
        return new Card(name, brand, income, initialLimit);
    }
}
