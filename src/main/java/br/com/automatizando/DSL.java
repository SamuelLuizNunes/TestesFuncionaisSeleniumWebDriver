package br.com.automatizando;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DSL {
    private WebDriver driver;

    public DSL(WebDriver driver){
        this.driver = driver;
    }
    public void escreve(String idCampo, String texto){
        driver.findElement(By.id(idCampo)).sendKeys(texto);
    }

    public void escreve(By by, String texto){
        driver.findElement(by).sendKeys(texto);
    }

    public String obterValorCampo(String idCampo){
        return driver.findElement(By.id(idCampo)).getAttribute("value");
    }

    public void clickRadioButton(String idCampo){
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean radioButtonSelecionado(String idCampo){
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    public void clickCheckBox(String idCampo){
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean checkBoxSelecionado(String idCampo){
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    public void selecionarCombo(String id, String valor){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clickBotao(String id){
        driver.findElement(By.id(id)).click();
    }

    public void clickLink(String link){
        driver.findElement(By.linkText(link)).click();
    }

    public String obterTexto(By by){
        return driver.findElement(by).getText();
    }
    public String obterTexto(String id){
        return obterTexto(By.id(id));
    }

    public String alertaObterTextoEAceita(){
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        alert.accept();
        return texto;
    }

    public String alertaObterTexto(){
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        return texto;
    }

    public void alertaEscreverEAceitar(String texto){
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(texto);
        alert.accept();
    }

    public String alertaObterTextoENega(){
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        alert.dismiss();
        return texto;
    }

    public void trocaJanela(String id){
        driver.switchTo().window(id);
    }

    public void entrarFrame(String id){
        driver.switchTo().frame(id);
    }

    public void sairFrame(){
        driver.switchTo().defaultContent();
    }

    public void trocarJanela(String id){
        driver.switchTo().window(id);
    }

    public Object executarJS(String cmd, Object... param){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(cmd, param);
    }
}
