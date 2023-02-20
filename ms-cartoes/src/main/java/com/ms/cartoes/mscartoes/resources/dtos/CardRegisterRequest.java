package com.ms.cartoes.mscartoes.resources.dtos;

import com.ms.cartoes.mscartoes.entities.Card;
import com.ms.cartoes.mscartoes.entities.enums.CardBrand;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CardRegisterRequest {

    @NotBlank(message = "{card.register.request.name.notblank}")
    private String name;

    @NotNull(message = "{card.register.request.brand.notnull}")
    private CardBrand brand;

    @NotNull(message = "{card.register.request.income.notnull}")
    private BigDecimal income;

    @NotNull(message = "{card.register.request.initialLimit.notnull}")
    private BigDecimal initialLimit;


    public Card toCard() {
        return new Card(name, brand, income, initialLimit);
    }
}
