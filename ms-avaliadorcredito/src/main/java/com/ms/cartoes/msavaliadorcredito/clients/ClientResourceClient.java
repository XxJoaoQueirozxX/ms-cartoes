package com.ms.cartoes.msavaliadorcredito.clients;

import com.ms.cartoes.msavaliadorcredito.domain.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="ms-clientes", path = "/clients")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> findClientByCpf(@RequestParam("cpf") String cpf);

}
