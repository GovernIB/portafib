package es.caib.portafib.it.actors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        if (!getTasksUrl().equals(webDriver.getCurrentUrl())) {
            webDriver.get(getTasksUrl());
        }
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/a[2]")).click();
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/ul/li[4]/a")).click();
    }

    public void rebutjarDarrera(String motiu) {
        if (!getTasksUrl().equals(webDriver.getCurrentUrl())) {
            webDriver.get(getTasksUrl());
        }
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/a[2]")).click();
        // TODO. Cercar alternativa https://github.com/SeleniumHQ/htmlunit-driver/issues/14
        /*
        webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/ul/li[5]/a")).click();
        Alert alert = webDriver.switchTo().alert();
        alert.sendKeys(motiu);
        alert.accept();*/
        /* Alternativa, agafam la URL de fullView, i canviam l'acció per rebutjar, i afegim el motiu */
        WebElement element = webDriver.findElement(By.xpath("//table/tbody/tr/td[last()]/div/ul/li[3]/a"));
        String hrefVistaCompleta = element.getAttribute("href");
        String hrefRebutjar = hrefVistaCompleta.replace("fullView", "rebutjar")
                + "?motiu=" + motiu;
        webDriver.get(hrefRebutjar);
    }
}
