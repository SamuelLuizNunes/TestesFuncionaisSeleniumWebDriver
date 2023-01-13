package br.com.automatizando.test;

import br.com.automatizando.core.DSL;
import br.com.automatizando.core.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
    private DSL dsl;

    @Before
    public void inicializa(){
        WebDriverManager.chromedriver().setup();
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finaliza(){
        DriverFactory.killDrive();
    }
    @Test
    public void deveInteragirComAlertSimples() {
        dsl.clickBotao("alert");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Alert Simples", texto);
        dsl.escreve("elementosForm:nome", texto);
    }

    @Test
    public void deveInteragirComAlertConfirm() {
        dsl.clickBotao("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
        Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());

        dsl.clickBotao("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
        Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
    }

    @Test
    public void deveInteragirComAlertPrompt() {
        dsl.clickBotao("prompt");
        Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
        dsl.alertaEscreverEAceitar("12");
        Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
        Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
    }
}
