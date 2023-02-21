package com.ms.cartoes.msavaliadorcredito.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientCreditRatingResponse {

    private List<ApprovedCard> approvedCards;

}
