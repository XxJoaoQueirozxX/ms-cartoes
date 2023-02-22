package com.ms.cartoes.mscartoes.msqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.cartoes.mscartoes.domain.CardIssuanceRequestData;
import com.ms.cartoes.mscartoes.entities.Card;
import com.ms.cartoes.mscartoes.entities.ClientCard;
import com.ms.cartoes.mscartoes.repositories.CardRepository;
import com.ms.cartoes.mscartoes.repositories.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = {"${mq.queues.cards-issuance}"})
    public void receiveIssuanceRequest(@Payload String payload) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final CardIssuanceRequestData cardIssuanceRequestData = objectMapper.readValue(payload, CardIssuanceRequestData.class);

            final Card card =  cardRepository.findById(cardIssuanceRequestData.getCardId()).orElseThrow();
            final ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(cardIssuanceRequestData.getCpf());
            clientCard.setLimit(cardIssuanceRequestData.getLimitApproved());
            clientCardRepository.save(clientCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
