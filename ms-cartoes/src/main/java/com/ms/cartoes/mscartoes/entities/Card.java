package com.ms.cartoes.mscartoes.entities;

import com.ms.cartoes.mscartoes.entities.enums.CardBrand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal initialLimit;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

    public Card(String name, CardBrand brand, BigDecimal income, BigDecimal initialLimit) {
        this.name = name;
        this.brand = brand;
        this.income = income;
        this.initialLimit = initialLimit;
    }
}
