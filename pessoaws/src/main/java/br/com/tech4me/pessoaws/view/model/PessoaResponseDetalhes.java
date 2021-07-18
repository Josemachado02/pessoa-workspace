package br.com.tech4me.pessoaws.view.model;

import java.util.List;

import br.com.tech4me.pessoaws.shared.Animal;

public class PessoaResponseDetalhes {
    private String id;
    private String nome;
    private List<Animal> animais;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Animal> getAnimais() {
        return animais;
    }
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
    
}
