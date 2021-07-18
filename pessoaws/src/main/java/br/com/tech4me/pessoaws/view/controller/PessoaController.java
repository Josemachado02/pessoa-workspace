package br.com.tech4me.pessoaws.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.pessoaws.service.PessoaService;
import br.com.tech4me.pessoaws.shared.PessoaDto;
import br.com.tech4me.pessoaws.view.model.PessoaRequest;
import br.com.tech4me.pessoaws.view.model.PessoaResponse;
import br.com.tech4me.pessoaws.view.model.PessoaResponseDetalhes;


@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaService servico;

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> obterTodasAsPessoas() {
        ModelMapper mapa = new ModelMapper();
        List<PessoaDto> pesDtos = servico.obterTodos();
        List<PessoaResponse> pesResponse = pesDtos.stream()
            .map(pes -> mapa.map(pes, PessoaResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(pesResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaResponseDetalhes> obterUmaPessoa(@PathVariable String id){
        Optional<PessoaDto> pes = servico.obterPorId(id);

        if (pes.isPresent()) {
            return new ResponseEntity<>(new ModelMapper().map(pes.get(), PessoaResponseDetalhes.class), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/status")
    public String status(@Value("${local.server.port}") String porta){
        return String.format("Serviço rodando na porta %s", porta);
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> criarUmaPessoa(@RequestBody @Valid PessoaRequest pessoa) {
        ModelMapper mapa = new ModelMapper();
        PessoaDto pesDto = mapa.map(pessoa, PessoaDto.class);
        pesDto = servico.criarPessoa(pesDto);
        return new ResponseEntity<>(mapa.map(pesDto, PessoaResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id){
        servico.removerPessoa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable String id, @RequestBody @Valid PessoaRequest pessoa) {
        ModelMapper mapa = new ModelMapper();
        PessoaDto pesDto = mapa.map(pessoa, PessoaDto.class);
        pesDto = servico.atualizarPessoa(id, pesDto);
        return new ResponseEntity<>(mapa.map(pesDto, PessoaResponse.class), HttpStatus.OK);
    }



    
}
