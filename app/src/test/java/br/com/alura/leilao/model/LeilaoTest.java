package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

// A cada teste é criada uma nova instância da classe de teste
public class LeilaoTest {

    public static final double DELTA = 0.0001;
    // Caso as propriedades fossem estáticas, não dependeriam de instância,
    // manteria-se a mesma referência em todos os testes o que faria um afetar o outro
    private final Leilao console = new Leilao("Console");
    private final Usuario alex = new Usuario("Alex");
    private final Usuario eu = new Usuario("Eu");

    @Test
    public void getDescricao_QuandoRecebeDescricao_RetornaDescricao() {
        // Executar ação
        String descricao = console.getDescricao();

        // Assert
        assertEquals("Console", descricao);
    }

    @Test
    public void getMaiorLance_QuandoRecebeApenasUmLance_DevolveMaiorLance() {
        console.propoe(new Lance(alex, 200.0));

        double maiorLance = console.getMaiorLance();

        assertEquals(200.0, maiorLance, DELTA);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente_DevolveMaiorLance() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(eu, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente_DevolveMaiorLance() {
        console.propoe(new Lance(alex, 10000.0));
        console.propoe(new Lance(eu, 9000.0));

        double maiorLanceDevolvidoCarro = console.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvidoCarro, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeApenasUmLance() {
        console.propoe(new Lance(alex, 200.0));

        double menorLance = console.getMenorLance();

        assertEquals(200.0, menorLance, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(eu, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeMaisDeUmLanceEmOrdemDecrescent() {
        console.propoe(new Lance(alex, 10000.0));
        console.propoe(new Lance(eu, 9000.0));

        double menorLanceDevolvidoCarro = console.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvidoCarro, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_quando_RecebeExatosTresLances() {
        console.propoe(new Lance(alex, 200.0));
        console.propoe(new Lance(eu, 300.0));
        console.propoe(new Lance(alex, 400.0));

        List<Lance> tresLances =  console.tresMaioresLances();
        assertEquals(3, tresLances.size());
        assertEquals(400, tresLances.get(0).getValor(), DELTA);
        assertEquals(300, tresLances.get(1).getValor(), DELTA);
        assertEquals(200, tresLances.get(2).getValor(), DELTA);
    }
}