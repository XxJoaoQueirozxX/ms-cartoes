package com.ms.cartoes.msavaliadorcredito.resources;

import com.ms.cartoes.msavaliadorcredito.domain.*;
import com.ms.cartoes.msavaliadorcredito.services.CreditRatingService;
import com.ms.cartoes.msavaliadorcredito.services.exceptions.CardIssuanceException;
import com.ms.cartoes.msavaliadorcredito.services.exceptions.ClientNotFoundException;
import com.ms.cartoes.msavaliadorcredito.services.exceptions.ServiceComunicationErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/credit-rating")
@RequiredArgsConstructor
public class CreditRatingController {

    private final CreditRatingService creditRatingService;

    @GetMapping("/status")
    public String status() {
        return "MS-AVALIADOR-CREDITO - OK";
    }

    @GetMapping(params = "cpf")
    public ResponseEntity queryClientSituation(@RequestParam("cpf") String cpf)  {
        try {
            final ClientSituation clientSituation = creditRatingService.getClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (ServiceComunicationErrorException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }


    @PostMapping
    public ResponseEntity realizeCreditRating(@RequestBody RatingData data) {
        try {
            final ClientCreditRatingResponse clientCreditRatingResponse = creditRatingService.realizeRating(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(clientCreditRatingResponse);
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (ServiceComunicationErrorException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }

}
