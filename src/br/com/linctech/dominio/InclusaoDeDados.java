package br.com.linctech.dominio;

import java.util.Set;

import br.com.linctech.auxiliar.ColecaoVaziaException;

public interface InclusaoDeDados {

    public abstract boolean cadastrarNome(Agenda agenda);

    public abstract boolean cadastrarTelefone(Agenda agenda) throws ColecaoVaziaException;

    public abstract boolean cadastrarEmail(Agenda agenda) throws ColecaoVaziaException;

    public abstract Pessoa pesquisarNome(Set<Pessoa> pessoas, String nome);

    public abstract Email pesquisarEmail(Set<Email> emails, String email);

    public abstract Telefone pesquisarTelefone(Set<Telefone> telefones, String telefone);

    public abstract void consultarDados(Agenda agenda) throws ColecaoVaziaException;

    public abstract boolean excluirPessoa(Agenda agenda) throws ColecaoVaziaException;

    public abstract boolean excluirTelefone(Agenda agenda) throws ColecaoVaziaException;

    public abstract boolean excluirEmail(Agenda agenda) throws ColecaoVaziaException;
}