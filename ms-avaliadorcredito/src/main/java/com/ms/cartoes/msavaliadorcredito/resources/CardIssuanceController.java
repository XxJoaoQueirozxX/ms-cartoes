package com.ms.cartoes.msavaliadorcredito.resources;


import com.ms.cartoes.msavaliadorcredito.domain.CardIssuanceProtocol;
import com.ms.cartoes.msavaliadorcredito.domain.CardIssuanceRequestData;
import com.ms.cartoes.msavaliadorcredito.services.CreditRatingService;
import com.ms.cartoes.msavaliadorcredito.services.exceptions.CardIssuanceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/card-issuance")
@RequiredArgsConstructor
public class CardIssuanceController {

    private final CreditRatingService creditRatingService;


    @PostMapping
    public ResponseEntity realizeCardIssuance(@RequestBody CardIssuanceRequestData data) {
        try {
            final CardIssuanceProtocol protocol = creditRatingService.requestCardIssuance(data);
            return ResponseEntity.ok(protocol);
        } catch (CardIssuanceException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
