package com.ms.cartoes.msavaliadorcredito.msqueue;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.cartoes.msavaliadorcredito.domain.CardIssuanceRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuancePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardIssuance;

    public void requestCardIssuance(CardIssuanceRequestData data) throws JsonProcessingException {
        final String json = convertToJson(data);
        rabbitTemplate.convertAndSend(queueCardIssuance.getName(), json);
    }

    private String convertToJson(CardIssuanceRequestData data) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }

}
