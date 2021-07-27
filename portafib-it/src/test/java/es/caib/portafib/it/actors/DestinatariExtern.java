package es.caib.portafib.it.actors;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.mail.Session;

public class DestinatariExtern implements Destinatari {

    private final String administrationId;
    private final String email;
    private final String name;
    private final String surnames;
    private final String pin;
    private final Inbox inbox;
    private final WebDriver webDriver;

    public DestinatariExtern(String administrationId, String email, String name, String surnames,
                             String pin, Session session) {
        this.administrationId = administrationId;
        this.email = email;
        this.name = name;
        this.surnames = surnames;
        this.pin = pin;
        this.inbox = new Inbox( session, email, "x");
        webDriver = new HtmlUnitDriver(BrowserVersion.CHROME, true) {
            @Override
            protected WebClient modifyWebClient(WebClient client) {
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

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String pollLastMessageUrl(long wait) {
        Inbox.Missatge missatge = inbox.pollLastMessage(wait);
        if (missatge == null) {
            return null;
        }
        System.out.println(missatge.getAssumpte());
        System.out.println(missatge.getContingut());
        Document document = Jsoup.parse(missatge.getContingut());
        Elements links = document.getElementsByAttribute("href");
        return links.get(0).attr("href");
    }

    public void signarUrl(String url) {
        webDriver.get(url);
        webDriver.findElement(By.cssSelector("a.btn:nth-child(1)")).click();
        webDriver.switchTo().frame("myiframe");
        webDriver.findElement(By.id("plugin_fire")).click();
        webDriver.findElement(By.cssSelector("a.button")).click();
        webDriver.findElement(By.id("pin")).sendKeys(pin);
        webDriver.findElement(By.cssSelector("button[type='submit']")).submit();
    }

    public boolean comprovarFirmaJaProcessada(String url) {
        webDriver.get(url);
        // /html/body/div[2]/div/div[1]/center/div/h4
        WebElement element = webDriver.findElement(By.cssSelector(".alert > h4:nth-child(2)"));
        System.out.println(element.getText());
        return element.getText().contains("ja ha sigut processada");
    }

}
