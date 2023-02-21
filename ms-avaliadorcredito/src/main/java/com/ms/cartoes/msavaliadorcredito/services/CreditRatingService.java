package com.ms.cartoes.msavaliadorcredito.services;

import com.ms.cartoes.msavaliadorcredito.clients.CardResourceClient;
import com.ms.cartoes.msavaliadorcredito.clients.ClientResourceClient;
import com.ms.cartoes.msavaliadorcredito.domain.*;
import com.ms.cartoes.msavaliadorcredito.services.exceptions.ClientNotFoundException;
import com.ms.cartoes.msavaliadorcredito.services.exceptions.ServiceComunicationErrorException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditRatingService {

    private final ClientResourceClient clientResourceClient;
    private final CardResourceClient cardResourceClient;


    public ClientSituation getClientSituation(final String cpf) throws ClientNotFoundException, ServiceComunicationErrorException {
        try {
            final ResponseEntity<ClientData> clientResponseData = clientResourceClient.findClientByCpf(cpf);
            final ResponseEntity<List<ClientCard>> cardsByClient = cardResourceClient.getCardsByClient(cpf);
            return ClientSituation
                    .builder()
                    .client(clientResponseData.getBody())
                    .cards(cardsByClient.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            final int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientNotFoundException("Client not found");
            }
            throw new ServiceComunicationErrorException(e.getMessage(), status);
        }
    }

    public ClientCreditRatingResponse realizeRating(final String cpf, final Long income) throws ClientNotFoundException, ServiceComunicationErrorException {
        try {
            final ResponseEntity<ClientData> clientResponseData = clientResourceClient.findClientByCpf(cpf);
            final ResponseEntity<List<Card>> cardsResponse = cardResourceClient.getCardsWhenIncomeIsLessThanEquals(income);
            final List<Card> cards = cardsResponse.getBody();
            final ClientData clientData = clientResponseData.getBody();
            final List<ApprovedCard> approvedCards = cards.stream().map(card -> {
                final BigDecimal initialLimit = card.getInitialLimit();
                final BigDecimal idadeBD = BigDecimal.valueOf(clientData.getAge());
                final BigDecimal fator = idadeBD.divide(BigDecimal.TEN);

                return ApprovedCard.builder()
                        .name(card.getName())
                        .brand(card.getBrand())
                        .limit(fator.multiply(initialLimit))
                        .build();
            }).collect(Collectors.toList());
            return new ClientCreditRatingResponse(approvedCards);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientNotFoundException("Client not found");
            }
            throw new ServiceComunicationErrorException(e.getMessage(), status);
        }
    }
}
