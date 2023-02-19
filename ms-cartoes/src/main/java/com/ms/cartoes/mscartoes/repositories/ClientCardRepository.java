package com.ms.cartoes.mscartoes.repositories;

import com.ms.cartoes.mscartoes.entities.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
    List<ClientCard> findByCpf(String cpf);

}
