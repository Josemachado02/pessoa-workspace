package br.com.tech4me.pessoaws.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pessoa")
public class Pessoa {
    @Id
    private String id;
    private String nome;
    private String sobrenome;
    private int idade;

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
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    @Override
    public String toString() {
        return "Pessoa [idade=" + idade + ", nome=" + nome + ", sobrenome=" + sobrenome + "]";
    }

    

    
    
}
