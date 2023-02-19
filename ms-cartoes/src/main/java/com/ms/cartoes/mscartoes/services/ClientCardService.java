package com.ms.cartoes.mscartoes.services;

import com.ms.cartoes.mscartoes.entities.ClientCard;
import com.ms.cartoes.mscartoes.repositories.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository repository;

    public List<ClientCard> getClientCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }


}
