package com.ms.cartoes.mscartoes.resources;

import com.ms.cartoes.mscartoes.entities.Card;
import com.ms.cartoes.mscartoes.entities.ClientCard;
import com.ms.cartoes.mscartoes.resources.dtos.CardRegisterRequest;
import com.ms.cartoes.mscartoes.resources.dtos.ClientCardResponse;
import com.ms.cartoes.mscartoes.services.CardService;
import com.ms.cartoes.mscartoes.services.ClientCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final ClientCardService clientCardService;


    @GetMapping("/status")
    public String status() {
        return "MS CARTOES - OK";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid CardRegisterRequest request) {
        final Card card = request.toCard();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCards(@RequestParam("income") Long income) {
        final List<Card> cards = cardService.getCardsIncomeLessThanEquals(income);
        return ResponseEntity.ok(cards);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCardResponse>> getCardsByClient(@RequestParam("cpf") String cpf) {
        final List<ClientCard> cardsByCpfClient = clientCardService.getClientCardsByCpf(cpf);
        final List<ClientCardResponse> cards = cardsByCpfClient.stream().map(ClientCardResponse::fromClientCard).collect(Collectors.toList());
        return ResponseEntity.ok(cards);
    }

}
