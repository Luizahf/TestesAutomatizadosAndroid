package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSequidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioDeuCincoLancesException;

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
        valida(lance);
        lances.add(lance);
        if (lances.size() == 1) {
            maiorLance = valorLance;
            menorLance = valorLance;
        }
        Collections.sort(lances);
        calculaMaiorLance(valorLance);
    }

    private void valida(Lance lance) {
        lanceMenorQueUltimoLance(lance);
        usuarioJaDeuLance(lance);
        usuarioJaDeuCincoLances(lance);
    }

    private void lanceMenorQueUltimoLance(Lance lance) {
        boolean menor = maiorLance > lance.getValor();
        if (menor) throw new LanceMenorQueUltimoLanceException();
    }

    private void usuarioJaDeuCincoLances(Lance lance) {
        int lancesDoUsuario = 0;
        for (Lance l : lances) {
            if (l.getUsuario().equals(lance.getUsuario()))
                lancesDoUsuario++;
        }
        boolean lancesDados = lancesDoUsuario == 5;
        if (lancesDados) throw new UsuarioDeuCincoLancesException();
    }

    private void usuarioJaDeuLance(Lance lance) {
        boolean jaDeu = !lances.isEmpty() &&
                lance.getUsuario().equals(lances.get(0).getUsuario());
        if (jaDeu) throw new LanceSequidoDoMesmoUsuarioException();
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
}
