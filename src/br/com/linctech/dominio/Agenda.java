package br.com.linctech.dominio;

import java.util.HashSet;
import java.util.Set;

public class Agenda {
    private Set<Pessoa> pessoas;

    public Agenda() {
        this.pessoas = new HashSet<>();
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }
}