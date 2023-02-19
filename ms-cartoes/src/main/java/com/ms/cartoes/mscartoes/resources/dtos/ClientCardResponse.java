package com.ms.cartoes.mscartoes.resources.dtos;

import com.ms.cartoes.mscartoes.entities.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCardResponse {
    private String name;
    private String brand;
    private BigDecimal limit;

    public static ClientCardResponse fromClientCard(ClientCard clientCard) {
        return new ClientCardResponse(
                clientCard.getCard().getName(),
                clientCard.getCard().getBrand().toString(),
                clientCard.getLimit());
    }

}
