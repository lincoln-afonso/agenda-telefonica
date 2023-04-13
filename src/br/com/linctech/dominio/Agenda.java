package br.com.linctech.dominio;

import java.io.Serializable;
import java.util.TreeSet;
import java.util.Set;

public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
    private Set<Pessoa> pessoas;

    public Agenda() {
        this.pessoas = new TreeSet<>();
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    @Override
    public String toString() {
        return "Agenda [pessoas=" + pessoas + "]\n";
    }

}