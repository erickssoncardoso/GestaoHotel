package model;

import java.util.ArrayList;
import java.util.List;
import model.acomodacao.Acomodacao;

public class Hotel {
    private int id;
    private String nome;
    private String localizacao;
    private List<Acomodacao> acomodacoes;

    public Hotel(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.acomodacoes = new ArrayList<>(); // Inicializa a lista
    }
    
    @Override
    public String toString() {
        return nome;
    }

    // Construtor que aceita ID, nome e localização
    public Hotel(int id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.acomodacoes = new ArrayList<>(); // Inicializa a lista
    }

    // Construtor sem argumentos
    public Hotel() {
        this.acomodacoes = new ArrayList<>(); // Inicializa a lista
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void addAcomodacao(Acomodacao acomodacao) {
        this.acomodacoes.add(acomodacao);
    }

    public List<Acomodacao> getAcomodacoes() {
        return acomodacoes;
    }

    public void setAcomodacoes(List<Acomodacao> acomodacoes) {
        this.acomodacoes = acomodacoes;
    }
}
