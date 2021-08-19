package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSequidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioDeuCincoLancesException;

import static org.junit.Assert.*;

// A cada teste é criada uma nova instância da classe de teste
public class LeilaoTest {

    public static final double DELTA = 0.0001;
    // Caso as propriedades fossem estáticas, não dependeriam de instância,
    // manteria-se a mesma referência em todos os testes o que faria um afetar o outro
    private final Leilao console = new Leilao("Console");
    private final Usuario alex = new Usuario("Alex");
    private final Usuario eu = new Usuario("Eu");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getDescricao_QuandoRecebeDescricao_RetornaDescricao() {
        // Executar ação
        String descricao = console.getDescricao();

        // Assert
        assertThat(descricao, is("Console"));
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
        try {
            console.propoe(new Lance(eu, 9000.0));
            fail("Era esperado runtime exception");
        } catch (RuntimeException e) {
            // Teste passou
        }

        double maiorLanceDevolvidoCarro = console.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvidoCarro, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeApenasUmLance() {
        console.propoe(new Lance(alex, 200.0));

        double menorLance = console.getMenorLance();
        assertThat(menorLance, equalTo(200.0));
    }

    @Test
    public void deve_DevolverMenorLance_quando_RecebeMaisDeUmLanceEmOrdemCrescente() {
        console.propoe(new Lance(alex, 100.0));
        console.propoe(new Lance(eu, 200.0));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_quando_RecebeExatosTresLances() {
        console.propoe(new Lance(alex, 200.0));
        console.propoe(new Lance(eu, 300.0));
        console.propoe(new Lance(alex, 400.0));

        List<Lance> tresLances =  console.tresMaioresLances();
        assertThat(tresLances, hasSize(3));
        assertThat(tresLances, contains(
                new Lance(alex, 400.0),
                new Lance(eu, 300.0),
                new Lance(alex, 200.0)
        ));
    }

    @Test
    public void deve_DevolverTresMaioresLances_quando_NaoRecebeLances() {
        List<Lance> tresLances =  console.tresMaioresLances();
        assertEquals(0, tresLances.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_quando_RecebeApenasUmLance() {
        console.propoe(new Lance(alex, 200.0));

        List<Lance> tresLances =  console.tresMaioresLances();
        assertEquals(1, tresLances.size());
        assertEquals(200, tresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_quando_RecebeApenasDoisLances() {
        console.propoe(new Lance(alex, 200.0));
        console.propoe(new Lance(eu, 300.0));

        List<Lance> tresLances =  console.tresMaioresLances();
        assertEquals(2, tresLances.size());
        assertEquals(200, tresLances.get(1).getValor(), DELTA);
        assertEquals(300, tresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_quando_RecebeApenasMaisDeTresLances() {
        console.propoe(new Lance(alex, 300.0));
        console.propoe(new Lance(eu, 400.0));
        console.propoe(new Lance(new Usuario("Fran"), 500.0));
        console.propoe(new Lance(eu, 600.0));

        List<Lance> tresLances =  console.tresMaioresLances();
        assertEquals(3, tresLances.size());
        assertEquals(400, tresLances.get(2).getValor(), DELTA);
        assertEquals(500, tresLances.get(1).getValor(), DELTA);
        assertEquals(600, tresLances.get(0).getValor(), DELTA);

        console.propoe(new Lance(alex, 700.0));
        tresLances =  console.tresMaioresLances();
        assertEquals(3, tresLances.size());
        assertEquals(500, tresLances.get(2).getValor(), DELTA);
        assertEquals(600, tresLances.get(1).getValor(), DELTA);
        assertEquals(700, tresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverZeroParaMaiorLance_qauando_NaoTiverLances() {
        double maiorLance = console.getMaiorLance();
        assertEquals(0.0, maiorLance, DELTA);
    }

    @Test
    public void deve_DevolverZeroParaMenorLance_qauando_NaoTiverLances() {
        double menorLance = console.getMenorLance();
        assertEquals(0.0, menorLance, DELTA);
    }

    @Test(expected =  LanceMenorQueUltimoLanceException.class)
    public void deve_LancarException_quando_ForMenorQueOMaiorLance() {
        console.propoe(new Lance(alex, 500.0));
        console.propoe(new Lance(eu, 400.0));
    }

    @Test(expected =  LanceSequidoDoMesmoUsuarioException.class)
    public void deve_LancarException_quando_ForMesmoUsuarioUltimoLance() {
        console.propoe(new Lance(eu, 400.0));
        console.propoe(new Lance(new Usuario("Eu"), 600.0));
    }

    @Test(expected =  UsuarioDeuCincoLancesException.class)
    public void deve_LancarException_quando_UsuarioDerCincoLances() {

        console.propoe(new Lance(eu, 100.0));
        console.propoe(new Lance(alex, 200.0));
        console.propoe(new Lance(eu, 300.0));
        console.propoe(new Lance(alex, 400.0));
        console.propoe(new Lance(eu, 500.0));
        console.propoe(new Lance(alex, 600.0));
        console.propoe(new Lance(eu, 700.0));
        console.propoe(new Lance(alex, 800.0));
        console.propoe(new Lance(eu, 900.0));
        console.propoe(new Lance(alex, 1000.0));
        console.propoe(new Lance(eu, 1100.0));
    }
}