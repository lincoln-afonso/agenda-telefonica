package br.com.linctech.dominio;

import java.util.Set;

public interface InclusaoDeDados {

    public abstract boolean cadastrarNome(Agenda agenda);

    public abstract boolean cadastrarTelefone(Agenda agenda);

    public abstract boolean cadastrarEmail(Agenda agenda);

    public abstract Pessoa pesquisarNome(Set<Pessoa> pessoas, String nome);

    public abstract Email pesquisarEmail(Set<Email> emails, String email);

    public abstract Telefone pesquisarTelefone(Set<Telefone> telefones, String telefone);

    public abstract boolean consultarDados(Agenda agenda);

    public abstract boolean excluirPessoa(Agenda agenda);

    public abstract boolean excluirTelefone(Agenda agenda);

    public abstract boolean excluirEmail(Agenda agenda);
}