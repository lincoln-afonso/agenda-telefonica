package br.com.linctech.auxiliar;

import java.io.File;
import java.io.IOException;
import br.com.linctech.dominio.Agenda;

public class InicializacaoArquivo {
    private File fileAgenda;

    public InicializacaoArquivo(String nomeCaminhoA) {
        Agenda agenda;

        this.fileAgenda = new File(nomeCaminhoA);

        if (!this.fileAgenda.exists()) {
            try {
                this.fileAgenda.createNewFile();
                agenda = new Agenda();
                Serializador.gravar(agenda, nomeCaminhoA);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public File getFileAgenda() {
        return fileAgenda;
    }
}