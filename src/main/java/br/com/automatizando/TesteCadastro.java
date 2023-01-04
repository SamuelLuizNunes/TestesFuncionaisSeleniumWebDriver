package br.com.automatizando;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

    private ChromeDriver driver;
    private DSL dsl;

    @Before
    public void inicializa(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza(){
        driver.quit();
    }

    @Test
    public void realizarCadastro() {
        dsl.escreve("elementosForm:nome","Samuel");
        dsl.escreve("elementosForm:sobrenome","Nunes");
        dsl.clickRadioButton("elementosForm:sexo:0");
        dsl.clickCheckBox("elementosForm:comidaFavorita:2");
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.clickBotao("elementosForm:cadastrar");

        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Samuel"));
        Assert.assertEquals("Sobrenome: Nunes", dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
        Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Corrida", dsl.obterTexto("descEsportes"));
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        dsl.clickBotao("elementosForm:cadastrar");
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobranomeObrigatorio() {
        dsl.escreve("elementosForm:nome", "Samuel");
        dsl.clickBotao("elementosForm:cadastrar");
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        dsl.escreve("elementosForm:nome", "Samuel");
        dsl.escreve("elementosForm:sobrenome","Nunes");
        dsl.clickBotao("elementosForm:cadastrar");
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        dsl.escreve("elementosForm:nome", "Samuel");
        dsl.escreve("elementosForm:sobrenome","Nunes");
        dsl.clickRadioButton("elementosForm:sexo:1");
        dsl.clickCheckBox("elementosForm:comidaFavorita:0");
        dsl.clickCheckBox("elementosForm:comidaFavorita:3");
        dsl.clickBotao("elementosForm:cadastrar");
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        dsl.escreve("elementosForm:nome", "Samuel");
        dsl.escreve("elementosForm:sobrenome","Nunes");
        dsl.clickRadioButton("elementosForm:sexo:1");
        dsl.clickCheckBox("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:esportes", "Karate");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        dsl.clickBotao("elementosForm:cadastrar");
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }
}
