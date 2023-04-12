package br.com.linctech.auxiliar;

public class Menu {
    private static void exibirOpceos() {
        System.out.println("MENU");
        System.out.println(" 1 - Incluir o nome de uma pessoa");
        System.out.println(" 2 - Incluir um telefone");
        System.out.println(" 3 - Incluir um email");
        System.out.println(" 4 - Consular os dados de uma pessoa a partir do nome");
        System.out.println(" 5 - Excluir uma pessoa pelo nome");
        System.out.println(" 6 - Excluir um telefone");
        System.out.println(" 7 - Excluir um email");
        System.out.println(" 8 - Encerrar\n");
    }

    public static void perguntarOpcao() {
        Menu.exibirOpceos();
        System.out.print("Informe o número que corresponde a opção desejada: ");
    }
}