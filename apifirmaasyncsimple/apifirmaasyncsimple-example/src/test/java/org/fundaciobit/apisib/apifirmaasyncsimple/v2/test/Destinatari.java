package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Destinatari {

    private final String administrationId;
    private final String pin;
    private final String firmesPendentsUrl;
    private final WebDriver webDriver;

    public Destinatari(String administrationId, final String username, final String password, String pin,
                       String firmesPendentsUrl) {
        this.administrationId = administrationId;
        this.pin = pin;
        this.firmesPendentsUrl = firmesPendentsUrl;

        webDriver = new HtmlUnitDriver(BrowserVersion.CHROME, true) {
            @Override
            protected WebClient modifyWebClient(WebClient client) {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, password));
                client.setCredentialsProvider(credentialsProvider);
                client.setIncorrectnessListener(new IncorrectnessListener() {
                    @Override
                    public void notify(String message, Object origin) { /*no op*/ }
                });
                client.getOptions().setCssEnabled(false);
                client.getOptions().setThrowExceptionOnScriptError(false);
                return super.modifyWebClient(client);
            }
        };
    }

    public String getAdministrationId() {
        return administrationId;
    }

    public int firmesPendents() {
        webDriver.get(firmesPendentsUrl);
        WebElement element = webDriver.findElement(By.cssSelector(".badge"));
        int firmes = 0;
        if (element.getText() != null && !element.getText().trim().isEmpty()) {
            firmes = Integer.parseInt(element.getText());
        }
        return firmes;
    }

    public void firmarDarreraPeticio() {
        if (firmesPendentsUrl.equals(webDriver.getCurrentUrl())) {
            webDriver.get(firmesPendentsUrl);
        }
        webDriver.findElement(By.id("selectedItems1")).click();
        webDriver.findElement(By.linkText("Firmar seleccionats")).click();
        webDriver.switchTo().frame("myiframe");
        webDriver.findElement(By.id("plugin_fire")).click();
        webDriver.findElement(By.cssSelector("a.button")).click();
        webDriver.findElement(By.id("pin")).sendKeys(pin);
        webDriver.findElement(By.cssSelector("button[type='submit']")).submit();
    }
}
