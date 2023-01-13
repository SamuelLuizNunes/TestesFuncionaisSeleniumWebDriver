package br.com.automatizando.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory(){}

    public static WebDriver getDriver(){
        if(driver == null){
            switch (Propriedades.browser){
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void killDrive(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
