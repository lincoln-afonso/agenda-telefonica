package br.com.linctech.dominio;

import java.io.Serializable;
import java.util.Set;

import br.com.linctech.auxiliar.DadoInvalidoException;
import br.com.linctech.auxiliar.DadoNaoInformadoException;

public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private Set<Telefone> telefones;
    private Set<Email> emails;

    public Pessoa() {
    };

    public Pessoa(int id) throws DadoInvalidoException {
        this.setId(id);
    }

    public Pessoa(String nome, int id) throws DadoNaoInformadoException, DadoInvalidoException {
        this.setNome(nome);
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws DadoInvalidoException {

        if (id <= 0)
            throw new DadoInvalidoException("ID inválido!");
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadoNaoInformadoException {
        if (nome.isEmpty())
            throw new DadoNaoInformadoException("Nome não foi informado!");
        this.nome = nome.toUpperCase();
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + "]\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
}