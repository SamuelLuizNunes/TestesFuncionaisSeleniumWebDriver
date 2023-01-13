package br.com.automatizando;

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
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.clickBotao("buttonDelay");
        dsl.escreve("novoCampo", "Deu certo");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void deveInteragirComEsperaExplicita() throws InterruptedException {
        dsl.clickBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "Deu certo");
    }
}
