package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ViafirmaSignStrategyImpl extends SignStrategy {

    @Override
    public void sign(String url, String pin) {
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS) ;
        driver.get(url);
        driver.findElement(By.id("plugin_viafirma")).click();
        driver.findElement(By.id("verify-code")).sendKeys(pin);
        driver.findElement(By.id("verify-button")).click();
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
