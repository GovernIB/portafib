package es.caib.portafib.it.actors;

import org.openqa.selenium.By;

public class Colaborador extends Usuari {

    public Colaborador(String administrationId, String username, String password, String baseUrl) {
        super(administrationId, username, password, baseUrl);
    }

    @Override
    protected String getRoleName() {
        return "ROLE_COLA";
    }

    @Override
    protected String getTasksPath() {
        return "/cola/estatDeFirmaPendent/list/1";
    }

    public void validarDarrera() {
        if (!getTasksUrl().equals(webDriver.getCurrentUrl())) {
            webDriver.get(getTasksUrl());
        }
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/a[2]")).click();
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/ul/li[4]/a")).click();
    }

}
