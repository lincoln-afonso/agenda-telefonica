package br.com.linctech.dominio;

import java.io.Serializable;

import br.com.linctech.auxiliar.DadoNaoInformadoException;

public class Telefone implements Serializable, Comparable<Telefone> {
    private static final long serialVersionUID = 1L;
    private String numeroTelefone;

    public Telefone() {
    }

    public Telefone(String telefone) throws DadoNaoInformadoException {
        this.setTelefone(numeroTelefone);
    }

    public String getTelefone() {
        return numeroTelefone;
    }

    public void setTelefone(String numeroTelefone) throws DadoNaoInformadoException {
        if (numeroTelefone.isEmpty())
            throw new DadoNaoInformadoException("Número de telefone não informado!");
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public String toString() {
        return "Telefone [numeroTelefone=" + numeroTelefone + "]\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numeroTelefone == null) ? 0 : numeroTelefone.hashCode());
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
        Telefone other = (Telefone) obj;
        if (numeroTelefone == null) {
            if (other.numeroTelefone != null)
                return false;
        } else if (!numeroTelefone.equals(other.numeroTelefone))
            return false;
        return true;
    }

    @Override
    public int compareTo(Telefone telefone) {
        return this.getTelefone().compareToIgnoreCase(telefone.getTelefone());
    }
}