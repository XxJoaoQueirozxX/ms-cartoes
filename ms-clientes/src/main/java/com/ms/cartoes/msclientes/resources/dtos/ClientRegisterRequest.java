package com.ms.cartoes.msclientes.resources.dtos;

import com.ms.cartoes.msclientes.entities.Client;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Data
public class ClientRegisterRequest {

    @CPF(message = "{client.register.request.cpf.valid}")
    private String cpf;

    @NotBlank(message = "{client.register.request.name.notblank}")
    private String name;

    private String age;

    public Client toClient() {
        return new Client(cpf, name, age);
    }

}
