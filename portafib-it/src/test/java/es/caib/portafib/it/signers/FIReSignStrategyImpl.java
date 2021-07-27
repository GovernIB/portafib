package es.caib.portafib.it.signers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FIReSignStrategyImpl extends SignStrategy {

    @Override
    public void sign(String url, String pin) {
        WebDriver driver = getWebDriver();
        driver.get(url);
        driver.findElement(By.id("plugin_fire")).click();
        driver.findElement(By.cssSelector("a.button")).click();
        driver.findElement(By.id("pin")).sendKeys(pin);
        driver.findElement(By.cssSelector("button[type='submit']")).submit();
    }
}
