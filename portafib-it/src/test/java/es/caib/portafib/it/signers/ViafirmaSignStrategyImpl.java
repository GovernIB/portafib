package es.caib.portafib.it.signers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

import java.util.concurrent.TimeUnit;

public class ViafirmaSignStrategyImpl extends SignStrategy {

    @Override
    public void sign(String url, String pin) {
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS) ;
        driver.get(url);
        driver.findElement(By.id("plugin_viafirma")).click();
        driver.findElement(By.id("verify-code")).sendKeys(pin);

        // Woraround. Amb Vaddin (el framework que empra fortress) no va bé només fer click
        // cal fer un dblClick, i per fer-lo cal emprar l'api interna de htmlunit.

        WebElement element = driver.findElement(By.id("verify-button"));

        HtmlUnitWebElement huElement = (HtmlUnitWebElement) element;
        HtmlUnitDriver huDriver = (HtmlUnitDriver) driver;
        huDriver.getMouse().doubleClick(huElement.getCoordinates());

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
