package br.com.linctech.dominio;

import br.com.linctech.auxiliar.DadoNaoInformadoException;

public class Email {
    private String enderecoEletronino;

    public Email() {
    }

    public Email(String endecoEletronico) throws DadoNaoInformadoException {
        this.setEnderecoEletronino(enderecoEletronino);
    }

    public String getEnderecoEletronino() {
        return enderecoEletronino;
    }

    public void setEnderecoEletronino(String enderecoEletronino) throws DadoNaoInformadoException {
        if (enderecoEletronino.isEmpty())
            throw new DadoNaoInformadoException("Email não informado!");

        this.enderecoEletronino = enderecoEletronino.toLowerCase();
    }

    @Override
    public String toString() {
        return "Email [enderecoEletronino=" + enderecoEletronino + "]\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((enderecoEletronino == null) ? 0 : enderecoEletronino.hashCode());
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
        Email other = (Email) obj;
        if (enderecoEletronino == null) {
            if (other.enderecoEletronino != null)
                return false;
        } else if (!enderecoEletronino.equals(other.enderecoEletronino))
            return false;
        return true;
    }
}