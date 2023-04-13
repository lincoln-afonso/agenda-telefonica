package br.com.linctech.dominio;

import java.io.Serializable;

import br.com.linctech.auxiliar.DadoNaoInformadoException;

public class Email implements Serializable, Comparable<Email> {
    private static final long serialVersionUID = 1L;
    private String enderecoEletronico;

    public Email() {
    }

    public Email(String endecoEletronico) throws DadoNaoInformadoException {
        this.setEnderecoEletronico(enderecoEletronico);
    }

    public String getEnderecoEletronico() {
        return enderecoEletronico;
    }

    public void setEnderecoEletronico(String enderecoEletronino) throws DadoNaoInformadoException {
        if (enderecoEletronino.isEmpty())
            throw new DadoNaoInformadoException("Email não informado!");

        this.enderecoEletronico = enderecoEletronino.toLowerCase();
    }

    @Override
    public String toString() {
        return "Email [enderecoEletronico=" + enderecoEletronico + "]\n";
    }

    @Override
    public int compareTo(Email email) {
        return this.getEnderecoEletronico().compareToIgnoreCase(email.getEnderecoEletronico());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((enderecoEletronico == null) ? 0 : enderecoEletronico.hashCode());
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
        if (enderecoEletronico == null) {
            if (other.enderecoEletronico != null)
                return false;
        } else if (!enderecoEletronico.equals(other.enderecoEletronico))
            return false;
        return true;
    }
}