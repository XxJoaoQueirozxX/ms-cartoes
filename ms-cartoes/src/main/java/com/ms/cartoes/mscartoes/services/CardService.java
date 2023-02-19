package com.ms.cartoes.mscartoes.services;

import com.ms.cartoes.mscartoes.entities.Card;
import com.ms.cartoes.mscartoes.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    @Transactional
    public Card save(Card card) {
        return cardRepository.save(card);
    }
    public List<Card> getCardsIncomeLessThanEquals(Long income) {
        BigDecimal incomeBigDecimal = BigDecimal.valueOf(income);
        return cardRepository.findCardByIncomeIsLessThanEqual(incomeBigDecimal);
    }

}
