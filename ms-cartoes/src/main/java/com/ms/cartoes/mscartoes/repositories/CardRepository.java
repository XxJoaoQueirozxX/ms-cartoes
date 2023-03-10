package com.ms.cartoes.mscartoes.repositories;

import com.ms.cartoes.mscartoes.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findCardByIncomeIsLessThanEqual(BigDecimal income);
}
