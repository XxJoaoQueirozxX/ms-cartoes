package com.ms.cartoes.msclientes.resources.dtos;

import com.ms.cartoes.msclientes.entities.Client;
import lombok.Data;

@Data
public class ClientRegisterRequest {

    private String cpf;

    private String name;

    private String age;

    public Client toClient() {
        return new Client(cpf, name, age);
    }

}
