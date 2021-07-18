package br.com.tech4me.pessoaws.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.pessoaws.shared.PessoaDto;

public interface PessoaService {
    List<PessoaDto> obterTodos();
    PessoaDto criarPessoa(PessoaDto pessoa);
    void removerPessoa(String id);
    Optional<PessoaDto> obterPorId(String id);
    PessoaDto atualizarPessoa(String id, PessoaDto pessoa);
}
