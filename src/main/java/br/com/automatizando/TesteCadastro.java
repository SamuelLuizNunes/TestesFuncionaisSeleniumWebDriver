package br.com.automatizando;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

    @Test
    public void realizarCadastro() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Samuel");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nunes");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        new Select(driver.findElement(By.id("elementosForm:escolaridade")))
                .selectByVisibleText("Superior");

        new Select(driver.findElement(By.id("elementosForm:esportes")))
                .selectByValue("Corrida");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Samuel"));
        Assert.assertEquals("Sobrenome: Nunes", driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: Corrida", driver.findElement(By.id("descEsportes")).getText());
        driver.quit();
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        driver.quit();
    }

    @Test
    public void deveValidarSobranomeObrigatorio() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Samuel");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Samuel");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nunes");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarComidaVegetariana() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Samuel");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nunes");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Samuel");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nunes");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        driver.quit();
    }
}
