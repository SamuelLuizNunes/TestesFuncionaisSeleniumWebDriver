package br.com.automatizando.test;

import br.com.automatizando.core.DSL;
import br.com.automatizando.core.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TesteSincronismo {
    private DSL dsl;

    @Before
    public void inicializa() {
        WebDriverManager.chromedriver().setup();
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finaliza() {
//        driver.quit();
    }


    @Test
    public void deveInteragirComEsperaFixa() throws InterruptedException {
        dsl.clickBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escreve("novoCampo", "Deu certo");
    }

    @Test
    public void deveInteragirComEsperaImplicita() throws InterruptedException {
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.clickBotao("buttonDelay");
        dsl.escreve("novoCampo", "Deu certo");
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void deveInteragirComEsperaExplicita() throws InterruptedException {
        dsl.clickBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "Deu certo");
    }
}
