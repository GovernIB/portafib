package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

import netx.jnlp.JNLPFile;
import netx.jnlp.LaunchException;
import netx.jnlp.Launcher;
import netx.jnlp.ParseException;
import netx.jnlp.runtime.ApplicationInstance;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.Robot;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Permission;

/**
 * TODO no acaba d'estar automatitzat
 */
public class MiniAppletSignStrategyImpl extends SignStrategy {

    @Override
    public void sign(String url, String pin) {
        System.out.println(url);
        WebDriver driver = getWebDriver();
        driver.get(url);
        driver.findElement(By.id("plugin_20")).click();

        try {
            String jnlpUrl = driver.getCurrentUrl();
            JNLPFile jnlpFile = new JNLPFile(new URL(jnlpUrl));
            Launcher launcher = new Launcher();

            ApplicationInstance applicationInstance = launcher.launch(jnlpFile);

            Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
            robot.waitForIdle();

            final FrameFixture frameFixture = WindowFinder.findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
                @Override
                protected boolean isMatching(Frame component) {
                    return true;
                }
            }).using(robot);

            System.setSecurityManager(new SecurityManager() {
                @Override
                public void checkPermission(Permission perm) {
                    if (perm.getName().startsWith("exitVM")) {
                        throw new SecurityException() {

                        };
                    }
                }
            });

            robot.waitForIdle();

            frameFixture.focus();
            frameFixture.button().focus().click();

            robot.waitForIdle();

            DialogFixture dialogFixture = WindowFinder.findDialog(new GenericTypeMatcher<Dialog>(Dialog.class) {
                @Override
                protected boolean isMatching(Dialog component) {
                    return true;
                }
            }).using(robot);

            robot.waitForIdle();

            dialogFixture.button(JButtonMatcher.withText("OK")).click();

            String isFinishedUrlString = jnlpUrl.substring(0, jnlpUrl.lastIndexOf('/')) + "/isfinished";
            URL isFinishedUrl = new URL(isFinishedUrlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) isFinishedUrl.openConnection();
            httpURLConnection.connect();
            while (httpURLConnection.getResponseCode() == 304) {
                Thread.sleep(5000);
                httpURLConnection = (HttpURLConnection) isFinishedUrl.openConnection();
                httpURLConnection.connect();
            }


            String finalUrl = jnlpUrl.substring(0, jnlpUrl.lastIndexOf('/')) + "/final";
            System.out.println("URl final : " + finalUrl);

            driver.get(finalUrl);

        } catch (MalformedURLException e) {
            throw new RuntimeException("Url invalida,", e);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsejant JNLP", e);
        } catch (IOException e) {
            throw new RuntimeException("Error llegint JNLP", e);
        } catch (LaunchException e) {
            throw new RuntimeException("Error arrancant JNLP", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.setSecurityManager(null);
        }

    }

    private static class ExitTrappedException extends SecurityException {
    }
}
