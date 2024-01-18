package com.example.authservice.client;

import com.example.authservice.dto.Proprietaire;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "taxe-service", url = "${application.config.prop-url}/proprietaire")
public interface PropClient {
    @GetMapping("/save")
    Proprietaire saveProprietaire(@RequestBody Proprietaire proprietaire) ;
}
