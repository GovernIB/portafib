package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors;

import org.openqa.selenium.By;

public class Destinatari extends Usuari {

    private final String pin;

    public Destinatari(String administrationId, final String username, final String password, String pin,
                       String baseUrl) {
        super(administrationId, username, password, baseUrl);
        this.pin = pin;
    }

    @Override
    protected String getRoleName() {
        return "ROLE_DEST";
    }

    @Override
    protected String getTasksPath() {
        return "/dest/estatDeFirmaPendent/list/1";
    }

    public void firmarDarreraPeticio() {
        if (!getTasksUrl().equals(webDriver.getCurrentUrl())) {
            webDriver.get(getTasksUrl());
        }
        webDriver.findElement(By.id("selectedItems1")).click();
        webDriver.findElement(By.linkText("Firmar seleccionats")).click();
        webDriver.switchTo().frame("myiframe");
        webDriver.findElement(By.id("plugin_fire")).click();
        webDriver.findElement(By.cssSelector("a.button")).click();
        webDriver.findElement(By.id("pin")).sendKeys(pin);
        webDriver.findElement(By.cssSelector("button[type='submit']")).submit();
    }

    public boolean colaboradorPendentDarrera() {
        webDriver.get(getTasksUrl());
        String textCola = webDriver.findElement(By.xpath("//table/tbody/tr[1]/td[7]/small")).getText();
        return textCola.equals("Pendent: 1/1");
    }

    public boolean colaboradorValidatDarrera() {
        webDriver.get(getTasksUrl());
        String textCola = webDriver.findElement(By.xpath("//table/tbody/tr[1]/td[7]/small")).getText();
        return textCola.equals("Validat: 1/1");
    }
}
