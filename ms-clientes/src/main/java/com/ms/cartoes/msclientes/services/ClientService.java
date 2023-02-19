package com.ms.cartoes.msclientes.services;

import com.ms.cartoes.msclientes.entities.Client;
import com.ms.cartoes.msclientes.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByCpf(String cpf) {
        return clientRepository.getClientByCpf(cpf);
    }


}
