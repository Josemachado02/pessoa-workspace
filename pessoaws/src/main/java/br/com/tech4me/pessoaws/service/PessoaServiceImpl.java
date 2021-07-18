package br.com.tech4me.pessoaws.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.pessoaws.integration.AnimaisFeignClient;
import br.com.tech4me.pessoaws.model.Pessoa;
import br.com.tech4me.pessoaws.repository.PessoaRepository;
import br.com.tech4me.pessoaws.shared.PessoaDto;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository repositorio;

    @Autowired
    private AnimaisFeignClient animaisMs;

    @Override
    public List<PessoaDto> obterTodos() {
        List<Pessoa> pessoas = repositorio.findAll();

        return pessoas.stream()
        .map(pessoa -> new ModelMapper().map(pessoa, PessoaDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public PessoaDto criarPessoa(PessoaDto pessoa) {
        ModelMapper mapa = new ModelMapper();
        Pessoa pes = mapa.map(pessoa, Pessoa.class);
        pes = repositorio.save(pes);
        return mapa.map(pes, PessoaDto.class);
    }

    @Override
    public void removerPessoa(String id) {
        repositorio.deleteById(id);
        
    }

    @Override
    public Optional<PessoaDto> obterPorId(String id) {
        Optional<Pessoa> pes = repositorio.findById(id);

        if (pes.isPresent()) {
            PessoaDto pesDto = new ModelMapper().map(pes.get(), PessoaDto.class);
            pesDto.setAnimais(animaisMs.obterPorDono(id));
            
            return Optional.of(pesDto);
        }
        return Optional.empty();
    }

    @Override
    public PessoaDto atualizarPessoa(String id, PessoaDto pessoa) {
        ModelMapper mapa = new ModelMapper();
        Pessoa pes = mapa.map(pessoa, Pessoa.class);
        pes.setId(id);
        pes = repositorio.save(pes);

        return mapa.map(pes, PessoaDto.class);
    }

  
    
}
