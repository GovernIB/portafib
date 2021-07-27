package es.caib.portafib.it.signers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public abstract class SignStrategy {

    private final static ThreadLocal<WebDriver> tlWebDriver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            return new HtmlUnitDriver(BrowserVersion.CHROME, true) {
                @Override
                protected WebClient modifyWebClient(WebClient client) {
                    client.getOptions().setCssEnabled(false);
                    client.getOptions().setSSLClientProtocols(new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" });
                    return super.modifyWebClient(client);
                }
            };
        }
    };

    protected WebDriver getWebDriver() {
        return tlWebDriver.get();
    }

    public abstract void sign(String url, String pin);
}
