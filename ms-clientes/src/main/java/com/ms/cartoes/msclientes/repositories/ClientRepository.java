package com.ms.cartoes.msclientes.repositories;

import com.ms.cartoes.msclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> getClientByCpf(String cpf);

}
