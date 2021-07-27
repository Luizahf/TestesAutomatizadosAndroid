package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricao() {
        // Cenário
        Leilao console = new Leilao("Console");

        // Executar ação
        String descricao = console.getDescricao();

        // Assert
        assertEquals("Console", descricao);
    }

    @Test
    public void getMaiorLance() {
        // Cenário
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        // Executar ação
        double maiorLance = console.getMaiorLance();

        // Assert
        assertEquals(200.0, maiorLance, 0.0001);
    }
}