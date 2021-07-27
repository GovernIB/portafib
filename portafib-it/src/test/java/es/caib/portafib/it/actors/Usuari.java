package es.caib.portafib.it.actors;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public abstract class Usuari {

    private final String administrationId;
    private final String username;

    protected final WebDriver webDriver;
    protected final String baseUrl;

    protected Usuari(String administrationId, final String username, final String password, String baseUrl) {
        this.administrationId = administrationId;
        this.username = username;
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

        this.baseUrl = baseUrl;
    }

    public int tasquesPendents() {
        webDriver.get(getTasksUrl());
        try {
            WebElement element = webDriver.findElement(By.id("avisos_" + getRoleName()));
            return Integer.parseInt(element.getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    protected abstract String getRoleName();

    protected abstract String getTasksPath();

    protected String getTasksUrl() {
        return baseUrl + getTasksPath();
    }

    public String getAdministrationId() {
        return administrationId;
    }

    public String getUsername() {
        return username;
    }
}
