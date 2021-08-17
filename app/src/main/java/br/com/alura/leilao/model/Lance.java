package br.com.alura.leilao.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Lance implements Serializable, Comparable<Lance> {

    private final Usuario usuario;
    private final double valor;

    public Lance(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public int compareTo(@NonNull Lance o) {
        return Double.compare(o.getValor(), valor);
    }

    public Usuario getUsuario() {
        return usuario;
    }


}
