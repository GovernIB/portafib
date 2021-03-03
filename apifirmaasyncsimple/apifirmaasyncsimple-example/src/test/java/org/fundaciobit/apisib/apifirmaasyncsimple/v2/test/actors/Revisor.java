package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class Revisor extends Usuari {

    public Revisor(String administrationId, String username, String password, String baseUrl) {
        super(administrationId, username, password, baseUrl);
    }

    @Override
    protected String getRoleName() {
        return "ROLE_REVI";
    }

    @Override
    protected String getTasksPath() {
        return "/revi/estatDeFirmaPendent/list/1";
    }

    public void acceptarDarrera() {
        if (getTasksUrl().equals(webDriver.getCurrentUrl())) {
            webDriver.get(getTasksUrl());
        }
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/a[2]")).click();
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/ul/li[4]/a")).click();
    }

    public void rebutjarDarrera(String motiu) {
        if (getTasksUrl().equals(webDriver.getCurrentUrl())) {
            webDriver.get(getTasksUrl());
        }
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/a[2]")).click();
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/ul/li[5]/a")).click();
        // TODO. Cercar alternativa https://github.com/SeleniumHQ/htmlunit-driver/issues/14
        Alert alert = webDriver.switchTo().alert();
        alert.sendKeys(motiu);
        alert.accept();
    }
}