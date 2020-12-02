package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public abstract class SignStrategy {

    private final static ThreadLocal<WebDriver> tlWebDriver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            return new HtmlUnitDriver(BrowserVersion.CHROME, true);
        }
    };

    protected WebDriver getWebDriver() {
        return tlWebDriver.get();
    }

    public abstract void sign(String url);
}
