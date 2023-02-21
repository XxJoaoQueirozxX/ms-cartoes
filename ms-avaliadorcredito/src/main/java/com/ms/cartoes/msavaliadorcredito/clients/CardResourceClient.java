package com.ms.cartoes.msavaliadorcredito.clients;

import com.ms.cartoes.msavaliadorcredito.domain.Card;
import com.ms.cartoes.msavaliadorcredito.domain.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ms-cartoes", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getCardsWhenIncomeIsLessThanEquals(@RequestParam("income") Long income);

}
