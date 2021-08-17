package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        double valorLance = lance.getValor();
        if (podeProporLance(lance)) {
            lances.add(lance);
            if (lances.size() == 1) {
                maiorLance = valorLance;
                menorLance = valorLance;
            }
            Collections.sort(lances);
            calculaMaiorLance(valorLance);
            calculaMenorLance(valorLance);
        }
    }

    private boolean podeProporLance(Lance lance) {
        return !(maiorLance > lance.getValor() || usuarioJaDeuLance(lance) || usuarioJaDeuCincoLances(lance));
    }

    private boolean usuarioJaDeuCincoLances(Lance lance) {
        int lancesDoUsuario = 0;
        for (Lance l : lances) {
            if (l.getUsuario().equals(lance.getUsuario()))
                lancesDoUsuario++;
        }
        return  lancesDoUsuario == 5;
    }

    private boolean usuarioJaDeuLance(Lance lance) {
        return !lances.isEmpty() &&
                lance.getUsuario().equals(lances.get(0).getUsuario());
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public List<Lance> tresMaioresLances() {
        int quantidadeLances = Math.min(lances.size(), 3);
        return lances.subList(0, quantidadeLances);
    }

    public int quantidadeLances() {
        return lances.size();
    }
}
