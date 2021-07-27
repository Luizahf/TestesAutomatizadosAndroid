package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricao_QuandoRecebeDescricao_RetornaDescricao() {
        // Cenário
        Leilao console = new Leilao("Console");

        // Executar ação
        String descricao = console.getDescricao();

        // Assert
        assertEquals("Console", descricao);
    }

    @Test
    public void getMaiorLance_QuandoRecebeApenasUmLance_DevolveMaiorLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        double maiorLance = console.getMaiorLance();

        assertEquals(200.0, maiorLance, 0.0001);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente_DevolveMaiorLance() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.0));
        computador.propoe(new Lance(new Usuario("Eu"), 200.0));

        double maiorLanceDevolvido = computador.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente_DevolveMaiorLance() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 10000.0));
        carro.propoe(new Lance(new Usuario("Eu"), 9000.0));

        double maiorLanceDevolvidoCarro = carro.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvidoCarro, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeApenasUmLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        double menorLance = console.getMenorLance();

        assertEquals(200.0, menorLance, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.0));
        computador.propoe(new Lance(new Usuario("Eu"), 200.0));

        double menorLanceDevolvido = computador.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeMaisDeUmLanceEmOrdemDecrescent() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 10000.0));
        carro.propoe(new Lance(new Usuario("Eu"), 9000.0));

        double menorLanceDevolvidoCarro = carro.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvidoCarro, 0.0001);
    }


}