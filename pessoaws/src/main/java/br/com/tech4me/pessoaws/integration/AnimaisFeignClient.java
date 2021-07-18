package br.com.tech4me.pessoaws.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tech4me.pessoaws.shared.Animal;

@FeignClient(name = "animais-ms")
public interface AnimaisFeignClient {
    @GetMapping(path = "/api/animais/{dono}/lista")
    List<Animal> obterPorDono(@PathVariable String dono);
    
}
