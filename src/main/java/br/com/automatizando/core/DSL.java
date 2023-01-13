package br.com.automatizando.core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    public void escreve(String idCampo, String texto){
        DriverFactory.getDriver().findElement(By.id(idCampo)).sendKeys(texto);
    }

    public void escreve(By by, String texto){
        DriverFactory.getDriver().findElement(by).sendKeys(texto);
    }

    public String obterValorCampo(String idCampo){
        return DriverFactory.getDriver().findElement(By.id(idCampo)).getAttribute("value");
    }

    public void clickRadioButton(String idCampo){
        DriverFactory.getDriver().findElement(By.id(idCampo)).click();
    }

    public boolean radioButtonSelecionado(String idCampo){
        return DriverFactory.getDriver().findElement(By.id(idCampo)).isSelected();
    }

    public void clickCheckBox(String idCampo){
        DriverFactory.getDriver().findElement(By.id(idCampo)).click();
    }

    public boolean checkBoxSelecionado(String idCampo){
        return DriverFactory.getDriver().findElement(By.id(idCampo)).isSelected();
    }

    public void selecionarCombo(String id, String valor){
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String id){
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clickBotao(String id){
        DriverFactory.getDriver().findElement(By.id(id)).click();
    }

    public void clickLink(String link){
        DriverFactory.getDriver().findElement(By.linkText(link)).click();
    }

    public String obterTexto(By by){
        return DriverFactory.getDriver().findElement(by).getText();
    }
    public String obterTexto(String id){
        return obterTexto(By.id(id));
    }

    public String alertaObterTextoEAceita(){
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String texto = alert.getText();
        alert.accept();
        return texto;
    }

    public String alertaObterTexto(){
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String texto = alert.getText();
        return texto;
    }

    public void alertaEscreverEAceitar(String texto){
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.sendKeys(texto);
        alert.accept();
    }

    public String alertaObterTextoENega(){
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String texto = alert.getText();
        alert.dismiss();
        return texto;
    }

    public void trocaJanela(String id){
        DriverFactory.getDriver().switchTo().window(id);
    }

    public void entrarFrame(String id){
        DriverFactory.getDriver().switchTo().frame(id);
    }

    public void sairFrame(){
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public void trocarJanela(String id){
        DriverFactory.getDriver().switchTo().window(id);
    }

    public Object executarJS(String cmd, Object... param){
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        return js.executeScript(cmd, param);
    }
}
