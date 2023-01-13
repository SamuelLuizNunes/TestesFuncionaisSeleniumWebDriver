package br.com.automatizando.test;

import br.com.automatizando.core.BaseTeste;
import br.com.automatizando.core.DriverFactory;
import br.com.automatizando.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteCadastro extends BaseTeste {
    private CampoTreinamentoPage page;

    @Before
    public void inicializa(){
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        page = new CampoTreinamentoPage();
    }

    @After
    public void finaliza(){
        DriverFactory.killDrive();
    }

    @Test
    public void realizarCadastro() {
        page.setNome("Samuel");
        page.setSobrenome("Nunes");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("Corrida");
        page.cadastrar();
        Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
        Assert.assertTrue(page.obterNomeCadastro().endsWith("Samuel"));
        Assert.assertEquals("Sobrenome: Nunes", page.obterSobrenomeCadastro());
        Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
        Assert.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
        Assert.assertEquals("Esportes: Corrida", page.obterEsporteCadastro());
    }
}
