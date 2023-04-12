package br.com.linctech.dominio;

import java.util.Set;

public interface InclusaoDeDados {
    /**
     * System.out.println("MENU"); System.out.println(" 1 - Incluir o nome de uma
     * pessoa"); System.out.println(" 2 - Incluir um telefone");
     * System.out.println(" 3 - Incluir um email"); System.out.println(" 4 -
     * Consular os dados de uma pessoa a partir do nome"); System.out.println(" 5 -
     * Excluir uma pessoa pelo nome"); System.out.println(" 6 - Excluir um
     * telefone"); System.out.println(" 7 - Excluir um email"); System.out.println("
     * 8 - Encerrar\n");
     */

    public abstract boolean cadastrarNome(Set<Pessoa> pessoas);

    public abstract boolean cadastrarTelefone(Set<Pessoa> pessoas);

    public abstract boolean cadastrarEmail(Set<Email> emails);

    public abstract boolean consultarDadas(Set<Pessoa> pessoas);

    public abstract boolean excluirPessoa(Set<Pessoa> pessoas);

    public abstract boolean excluirTelefone(Set<Pessoa> pessoas);

    public abstract boolean excluirEmail(Set<Pessoa> pessoas);
}