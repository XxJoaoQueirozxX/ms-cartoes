package com.ms.cartoes.msclientes.resources;

import com.ms.cartoes.msclientes.entities.Client;
import com.ms.cartoes.msclientes.resources.dtos.ClientRegisterRequest;
import com.ms.cartoes.msclientes.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity registerClient(@RequestBody ClientRegisterRequest request) {
        Client client = request.toClient();
        client = clientService.save(client);
        final URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(client.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();
    }


    @GetMapping(params = "cpf")
    public ResponseEntity findClientByCpf(@RequestParam("cpf") String cpf) {
        final Optional<Client> client = clientService.getByCpf(cpf);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client.get());
    }


    @GetMapping("/status")
    public String status() {
        log.info("Verificando status do ms de clients");
        return "MS-CLIENT - OK";
    }

}
