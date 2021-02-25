package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ViafirmaSignStrategyImpl extends SignStrategy {

    @Override
    public void sign(String url) {
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.get(url);
        driver.findElement(By.id("plugin_viafirma")).click();
        driver.findElement(By.cssSelector(".v-textfield")).sendKeys("1234");
        driver.findElement(By.cssSelector(".primary")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
