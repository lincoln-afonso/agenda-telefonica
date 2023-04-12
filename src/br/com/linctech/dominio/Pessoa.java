package br.com.linctech.dominio;

import java.util.Set;

import br.com.linctech.auxiliar.DadoNaoInformadoException;

public class Pessoa {
    private int id;
    private String nome;
    private Set<Telefone> telefones;
    private Set<Email> emails;

    public Pessoa() {
    }

    public Pessoa(String nome) throws DadoNaoInformadoException {
        this.setNome(nome);
    }

    public Pessoa(String nome, String id) throws DadoNaoInformadoException {
        this.setNome(nome);
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(String id) throws DadoNaoInformadoException {
        int idPessoa;

        if (id.isEmpty())
            throw new DadoNaoInformadoException("ID não informado!");

        idPessoa = Integer.parseInt(id);
        this.id = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadoNaoInformadoException {
        if (nome.isEmpty())
            throw new DadoNaoInformadoException("Nome não foi informado!");
        this.nome = nome;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    @Override
    public String toString() {
        return "Pessoa [nome=" + nome + "]\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

}