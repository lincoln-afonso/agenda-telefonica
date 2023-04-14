package app;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import br.com.linctech.auxiliar.ColecaoVaziaException;
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

    public static void main(String[] args) {
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
                try {
                    if (app.cadastrarTelefone(agenda)) {
                        System.out.println("Telfone cadastrado!\n");
                        Serializador.gravar(agenda, inicializa.getFileAgenda().getName());
                    } else
                        System.out.println("Telefone não cadastrado!\n");
                } catch (ColecaoVaziaException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;

            case "3":
                try {
                    if (app.cadastrarEmail(agenda)) {
                        System.out.println("Email cadastrado!\n");
                        Serializador.gravar(agenda, inicializa.getFileAgenda().getName());
                    } else
                        System.out.println("Email não cadastrado!\n");
                } catch (ColecaoVaziaException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;

            case "4":
                try {
                    app.consultarDados(agenda);
                } catch (ColecaoVaziaException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;

            case "5":
                try {
                    if (app.excluirPessoa(agenda)) {
                        Serializador.gravar(agenda, inicializa.getFileAgenda().getName());
                        System.out.println("Pessoa/contato excluído com sucesso!\n");
                    } else
                        System.out.println("Não foi possível excluir a pessoa/contato!\n");
                } catch (ColecaoVaziaException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;

            case "6":
                try {
                    if (app.excluirTelefone(agenda)) {
                        Serializador.gravar(agenda, inicializa.getFileAgenda().getName());
                        System.out.println("Telefone excluído com sucesso!\n");
                    } else
                        System.out.println("Não foi possível excluir o número de telefone!\n");
                } catch (ColecaoVaziaException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;

            case "7":
                try {
                    if (app.excluirEmail(agenda)) {
                        Serializador.gravar(agenda, inicializa.getFileAgenda().getName());
                        System.out.println("Email excluído com sucesso!\n");
                    } else
                        System.out.println("Não foi possível excluir email!\n");
                } catch (ColecaoVaziaException e) {
                    System.out.println(e.getMessage() + "\n");
                }
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
                nome = this.lerNome(pessoas);
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
    public boolean cadastrarTelefone(Agenda agenda) throws ColecaoVaziaException {
        String nome;
        String numeroTelefone;
        Telefone telefone = new Telefone();
        Pessoa pessoa = new Pessoa();
        Set<Pessoa> pessoas = agenda.getPessoas();
        boolean eValido;

        if (pessoas.size() == 0)
            throw new ColecaoVaziaException("Não há pessoas cadastradas!");

        nome = this.lerNome(pessoas);

        pessoa = this.pesquisarNome(pessoas, nome);
        if (pessoa != null) {
            eValido = false;
            do {
                try {
                    System.out.print("Informe o  número de telefone: ");
                    numeroTelefone = this.getLeia().nextLine();
                    telefone.setTelefone(numeroTelefone);

                    eValido = true;
                } catch (DadoNaoInformadoException e) {
                    System.out.println(e.getMessage());
                }
            } while (eValido == false);
            return pessoa.getTelefones().add(telefone);
        } else
            System.out.println("Nome não encontrado!");
        return false;
    }

    @Override
    public boolean cadastrarEmail(Agenda agenda) throws ColecaoVaziaException {
        String enderecoEletronico;
        String nome;
        Email email = new Email();
        Pessoa pessoa = new Pessoa();
        Set<Pessoa> pessoas = agenda.getPessoas();
        boolean eValido;

        if (pessoas.size() == 0)
            throw new ColecaoVaziaException("Não há pessoas cadastradas!");

        nome = this.lerNome(pessoas);

        pessoa = this.pesquisarNome(pessoas, nome);
        if (pessoa != null) {
            eValido = false;
            do {
                System.out.print("Informe o email: ");
                enderecoEletronico = this.getLeia().nextLine();
                try {
                    email.setEnderecoEletronico(enderecoEletronico);
                    eValido = true;
                } catch (DadoNaoInformadoException e) {
                    System.out.println(e.getMessage());
                }
            } while (eValido == false);
            System.out.println(email);
            return pessoa.getEmails().add(email);
        } else
            System.out.println("Nome não encontrado!");
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
            if (em.getEnderecoEletronico().equalsIgnoreCase(email))
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

    public void listarTelefones(Set<Telefone> telefones) {
        Telefone numeroTelefone;

        if (telefones.size() > 0)
            System.out.println("Telefone(s)");

        Iterator<Telefone> tel = telefones.iterator();
        while (tel.hasNext()) {
            numeroTelefone = tel.next();
            System.out.println(numeroTelefone.getTelefone());
        }
        System.out.println();
    }

    public void listarEmails(Set<Email> emails) {
        Email enderecoEletronico;

        if (emails.size() > 0)
            System.out.println("Email(s)");

        Iterator<Email> em = emails.iterator();
        while (em.hasNext()) {
            enderecoEletronico = em.next();
            System.out.println(enderecoEletronico.getEnderecoEletronico());
        }
        System.out.println();
    }

    @Override
    public void consultarDados(Agenda agenda) throws ColecaoVaziaException {
        String nome;
        Pessoa pessoa = new Pessoa();
        Set<Pessoa> pessoas = agenda.getPessoas();

        if (pessoas.size() == 0)
            throw new ColecaoVaziaException("Não há pessoas cadastradas!");

        nome = this.lerNome(pessoas);

        pessoa = this.pesquisarNome(pessoas, nome);
        if (pessoa != null) {
            System.out.println("Nome: " + pessoa.getNome());
            this.listarTelefones(pessoa.getTelefones());
            this.listarEmails(pessoa.getEmails());
        } else
            System.out.println("Não há pessoas cadastradas com o nome " + nome + "!\n");
    }

    @Override
    public boolean excluirPessoa(Agenda agenda) throws ColecaoVaziaException {
        Pessoa pessoa = new Pessoa();
        String nome;
        Set<Pessoa> pessoas = agenda.getPessoas();

        if (pessoas.size() == 0)
            throw new ColecaoVaziaException("Não há pessoas cadastradas!");

        nome = this.lerNome(pessoas);

        pessoa = this.pesquisarNome(pessoas, nome);
        if (pessoa != null)
            return pessoas.remove(pessoa);
        else
            System.out.println("Não há pessoas cadastradas com o nome informado!");
        return false;
    }

    @Override
    public boolean excluirTelefone(Agenda agenda) throws ColecaoVaziaException {
        String nome;
        String numeroTelefone;
        Pessoa pessoa;
        Telefone telefone = new Telefone();
        boolean eValido;

        if (agenda.getPessoas().size() == 0)
            throw new ColecaoVaziaException("Não há pessoas cadastradas!");

        nome = this.lerNome(agenda.getPessoas());

        pessoa = this.pesquisarNome(agenda.getPessoas(), nome);
        if (pessoa != null) {
            if (pessoa.getTelefones().size() == 0)
                throw new ColecaoVaziaException("A pessoa informada não possui telefones cadastrados!");

            eValido = false;
            do {
                System.out.print("Informe o número do telefone a ser excluído: ");
                numeroTelefone = this.getLeia().nextLine();
                try {
                    telefone.setTelefone(numeroTelefone);
                    telefone = this.pesquisarTelefone(pessoa.getTelefones(), numeroTelefone);

                    if (telefone != null)
                        return pessoa.getTelefones().remove(telefone);
                    else
                        System.out.println(
                                pessoa.getNome() + " não possui o telefone " + numeroTelefone + " cadastrado!");

                    eValido = true;
                } catch (DadoNaoInformadoException e) {
                    System.out.println(e.getMessage());
                }
            } while (eValido == false);
        } else
            System.out.println("Não há ninguém cadastrado com o nome informado!");
        return false;
    }

    @Override
    public boolean excluirEmail(Agenda agenda) throws ColecaoVaziaException {
        String enderecoEletronico;
        String nome;
        Pessoa pessoa = new Pessoa();
        Email email = new Email();
        boolean eValido;

        if (agenda.getPessoas().size() == 0)
            throw new ColecaoVaziaException("Não há pessoas cadastradas!");

        nome = this.lerNome(agenda.getPessoas());

        pessoa = this.pesquisarNome(agenda.getPessoas(), nome);
        if (pessoa != null) {
            if (pessoa.getEmails().size() == 0)
                throw new ColecaoVaziaException("A pessoa informada não possui emails cadastrados!");

            do {
                eValido = false;
                System.out.print("Informe o email a ser excluído: ");
                enderecoEletronico = this.getLeia().nextLine();

                try {
                    email.setEnderecoEletronico(enderecoEletronico);
                    email = this.pesquisarEmail(pessoa.getEmails(), enderecoEletronico);

                    if (email != null)
                        return pessoa.getEmails().remove(email);
                    else
                        System.out.println(
                                pessoa.getNome() + " não possui o email " + enderecoEletronico + " cadastrado!");
                    eValido = true;
                } catch (DadoNaoInformadoException e) {
                    System.out.println(e.getMessage());
                }
            } while (eValido == false);
        } else
            System.out.println("Não há ninguém cadastrado com o nome informado!");
        return false;
    }

    public String lerNome(Set<Pessoa> pessoas) {
        String nome;
        boolean eValido;

        do {
            eValido = false;
            System.out.print("Informe o nome da pessoa: ");
            nome = this.getLeia().nextLine();

            try {
                if (nome.isEmpty())
                    throw new DadoNaoInformadoException("Nome não informado!");

                eValido = true;
            } catch (DadoNaoInformadoException e) {
                System.out.println(e.getMessage());
            }
        } while (eValido == false);
        return nome;
    }
}