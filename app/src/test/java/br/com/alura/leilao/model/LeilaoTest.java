package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

// A cada teste é criada uma nova instância da classe de teste
public class LeilaoTest {

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

        assertEquals(200.0, maiorLance, 0.0001);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente_DevolveMaiorLance() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(eu, 200.0));

        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente_DevolveMaiorLance() {
        console.propoe(new Lance(alex, 10000.0));
        console.propoe(new Lance(eu, 9000.0));

        double maiorLanceDevolvidoCarro = console.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvidoCarro, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeApenasUmLance() {
        console.propoe(new Lance(alex, 200.0));

        double menorLance = console.getMenorLance();

        assertEquals(200.0, menorLance, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(eu, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeMaisDeUmLanceEmOrdemDecrescent() {
        console.propoe(new Lance(alex, 10000.0));
        console.propoe(new Lance(eu, 9000.0));

        double menorLanceDevolvidoCarro = console.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvidoCarro, 0.0001);
    }


}