package app;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import br.com.linctech.auxiliar.DadoInvalidoException;
import br.com.linctech.auxiliar.DadoNaoInformadoException;
import br.com.linctech.auxiliar.InicializacaoArquivo;
import br.com.linctech.auxiliar.Menu;
import br.com.linctech.auxiliar.Serializador;
import br.com.linctech.dominio.Agenda;
import br.com.linctech.dominio.Email;
import br.com.linctech.dominio.InclusaoDeDados;
import br.com.linctech.dominio.Pessoa;
import br.com.linctech.dominio.Telefone;

/* Projeto simples que implementa uma agenda telefônica
 * Faça um programa que funcione como uma agenda eletrônica. O programa deve fornecer as
 * seguintes funcionalidades:

 * 1- incluir o nome de uma pessoa(o nome é único) e associar um id a ela. Começar com o id 1.
 * 2- incluir um telefone( uma pessoa pode possuir mais de um telefone)
 * 3- incluir um email ( uma pessoa pode possuir mais de um email)
 * 4- consular os dados de uma pessoa a partir do nome. Mostrar todos os telefones e emails
 * 5- excluir uma pessoa pelo nome (excluir todos os telefones e emails associados a pessoa)
 * 6- excluir um telefone de uma pessoa
 * 7- excluir um email de uma pessoa
 * 8- encerrar programa 
 */

public class App implements InclusaoDeDados {
    private Scanner leia;

    public App() {
        this.leia = new Scanner(System.in);
    }

    public Scanner getLeia() {
        return leia;
    }

    public static void main(String[] args) throws Exception {
        InicializacaoArquivo inicializa = new InicializacaoArquivo("agenda_telefonica");
        Agenda agenda;
        App app = new App();
        String opcao;

        do {
            agenda = (Agenda) Serializador.recuperar(inicializa.getFileAgenda().getName());
            Menu.perguntarOpcao();
            opcao = app.getLeia().nextLine();

            switch (opcao) {
            case "1":
                if (app.cadastrarNome(agenda)) {
                    System.out.println("Nome cadastrado!\n");
                    Serializador.gravar(agenda, inicializa.getFileAgenda().getName());
                } else
                    System.out.println("Nome não cadastrado!\n");
                break;

            case "2":

                break;

            case "3":

                break;

            case "4":

                break;

            case "5":

                break;

            case "6":

                break;

            case "7":
                System.out.println(agenda);
                break;

            case "8":
                System.out.println("Programa Encerrado!\n");
                break;

            default:
                System.out.println("Opção inválida!\n");
                break;
            }

        } while (!opcao.equals("8"));
        app.getLeia().close();
    }

    @Override
    public boolean cadastrarNome(Agenda agenda) {
        Set<Pessoa> pessoas = agenda.getPessoas();
        Pessoa pessoa;
        boolean eValido;
        String nome;

        do {
            eValido = false;
            try {
                pessoa = new Pessoa(pessoas.size() + 1);

                System.out.println("ID: " + pessoa.getId());

                System.out.print("Informe o nome: ");
                nome = this.getLeia().nextLine();
                pessoa.setNome(nome);

                return pessoas.add(pessoa);
            } catch (DadoInvalidoException e) {
                System.out.println();
            } catch (DadoNaoInformadoException e) {
                System.out.println(e.getMessage());
            }
        } while (eValido == false);
        return false;
    }

    @Override
    public boolean cadastrarTelefone(Agenda agenda) {
        return false;
    }

    @Override
    public boolean cadastrarEmail(Agenda agenda) {
        return false;
    }

    @Override
    public Pessoa pesquisarNome(Set<Pessoa> pessoas, String nome) {
        Pessoa pessoa;

        Iterator<Pessoa> p = pessoas.iterator();
        while (p.hasNext()) {
            pessoa = p.next();
            if (pessoa.getNome().equalsIgnoreCase(nome.toUpperCase()))
                return pessoa;
        }
        return null;
    }

    @Override
    public Email pesquisarEmail(Set<Email> emails, String email) {
        Email em;

        Iterator<Email> e = emails.iterator();
        while (e.hasNext()) {
            em = e.next();
            if (em.getEnderecoEletronino().equalsIgnoreCase(email))
                return em;
        }
        return null;
    }

    @Override
    public Telefone pesquisarTelefone(Set<Telefone> telefones, String numeroTelefone) {
        Telefone numeroTel;

        Iterator<Telefone> t = telefones.iterator();
        while (t.hasNext()) {
            numeroTel = t.next();
            if (numeroTel.getTelefone().equals(numeroTelefone))
                return numeroTel;
        }
        return null;
    }

    @Override
    public boolean consultarDados(Agenda agenda) {
        return false;
    }

    @Override
    public boolean excluirPessoa(Agenda agenda) {
        return false;
    }

    @Override
    public boolean excluirTelefone(Agenda agenda) {
        return false;
    }

    @Override
    public boolean excluirEmail(Agenda agenda) {
        return false;
    }
}