package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * La idea és capturar la URL del tipus "autofirma://" i cridar al binari local de autofirma.
 * Però les versions de Selenium/HtmlUnit compatibles amb Java 7 no permeten capturar aquestes URL,
 * l'HTMLUnit llança un error.
 */
public class AutofirmaSignStrategyImpl extends SignStrategy {

    @Override
    public void sign(String url) {
        WebDriver driver = getWebDriver();
        driver.get(url);
        driver.findElement(By.id("plugin_autofirma")).click();
        throw new UnsupportedOperationException("No soportat encara");
    }
}
