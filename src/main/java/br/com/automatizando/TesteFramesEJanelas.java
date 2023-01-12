package br.com.automatizando;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {

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
    public void deveInteragirComFrames() {
        dsl.entrarFrame("frame1");
        dsl.clickBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
        dsl.sairFrame();
        dsl.escreve("elementosForm:nome", msg);
    }

    @Test
    public void deveInteragirComJanelas() {
        dsl.clickBotao("buttonPopUpEasy");
        dsl.trocaJanela("Popup");
        dsl.escreve(By.tagName("textarea"),"Deu certo?");
        driver.close();
        dsl.trocaJanela("");
        dsl.escreve(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo() {
        dsl.clickBotao("buttonPopUpHard");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        dsl.escreve(By.tagName("textarea"), "Deu certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        dsl.escreve(By.tagName("textarea"), "E agora?");
    }
}










