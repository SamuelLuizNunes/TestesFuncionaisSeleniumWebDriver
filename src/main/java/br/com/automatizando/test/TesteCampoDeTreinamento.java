package br.com.automatizando.test;

import br.com.automatizando.core.DSL;
import br.com.automatizando.core.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoDeTreinamento {

    private DSL dsl;

    @Before
    public void inicializa() {
        WebDriverManager.chromedriver().setup();
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finaliza() {
        DriverFactory.killDrive();
    }

    @Test
    public void testeTextField() {
        dsl.escreve("elementosForm:nome", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComTextArea() {
        dsl.escreve("elementosForm:sugestoes", "teste");
        Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clickRadioButton("elementosForm:sexo:0");
        Assert.assertTrue(dsl.radioButtonSelecionado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckBox() {
        dsl.clickCheckBox("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.checkBoxSelecionado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
    }
    @Test
    public void deveVerificarValoresCombo() {
        WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes","Natacao");
        dsl.selecionarCombo("elementosForm:esportes","Corrida");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");

        WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clickBotao("buttonSimple");
        WebElement botao = DriverFactory.getDriver().findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveInteragirComLinks() {
        dsl.clickLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                dsl.obterTexto(By.className("facilAchar")));
    }
}
