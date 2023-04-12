package br.com.linctech.auxiliar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.linctech.dominio.Pessoa;
import br.com.linctech.dominio.Email;
import br.com.linctech.dominio.Telefone;

public class InicializacaoArquivo {
    private File filePessoa;

    public InicializacaoArquivo(String nomeCaminhoA) {
        this.filePessoa = new File(nomeCaminhoA);

        if (!this.filePessoa.exists()) {
            try {
                Set<Pessoa> setPessoas = new TreeSet<>();
                this.filePessoa.createNewFile();
                Serializador.gravar(setPessoas, nomeCaminhoA);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public File getFilePessoa() {
        return filePessoa;
    }
}