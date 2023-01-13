package br.com.automatizando.test;

import br.com.automatizando.core.DSL;
import br.com.automatizando.core.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {

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
    public void deveInteragirComFrames() {
        dsl.entrarFrame("frame1");
        dsl.clickBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
    }

    @Test
    public void deveInteragirComFrameEscondido(){
        WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        dsl.entrarFrame("frame2");
        dsl.clickBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
    }

    @Test
    public void deveInteragirComJanelas() {
        dsl.clickBotao("buttonPopUpEasy");
        dsl.trocaJanela("Popup");
        dsl.escreve(By.tagName("textarea"),"Deu certo?");
        DriverFactory.getDriver().close();
        dsl.trocaJanela("");
        dsl.escreve(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo() {
        dsl.clickBotao("buttonPopUpHard");
        DriverFactory.getDriver().switchTo().window((String) DriverFactory.getDriver().getWindowHandles().toArray()[1]);
        dsl.escreve(By.tagName("textarea"), "Deu certo?");
        DriverFactory.getDriver().switchTo().window((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]);
        dsl.escreve(By.tagName("textarea"), "E agora?");
    }


}










