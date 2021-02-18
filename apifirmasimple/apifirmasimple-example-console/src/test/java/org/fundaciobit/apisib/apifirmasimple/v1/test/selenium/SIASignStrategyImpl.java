package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * TODO. Només funciona quan el plugin SIA té un sòl certificat, i està configurat skip_certificate_selection.
 */
public class SIASignStrategyImpl extends SignStrategy {
    @Override
    public void sign(String url) {
        WebDriver driver = getWebDriver();
        driver.get(url);
        driver.findElement(By.id("plugin_sia")).click();
        driver.findElement(By.id("pin")).sendKeys("FBit123123");
        driver.findElement(By.id("btnFirmar")).click();
    }
}
