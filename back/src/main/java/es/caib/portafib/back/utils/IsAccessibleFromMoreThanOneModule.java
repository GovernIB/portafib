package es.caib.portafib.back.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 
 * @author anadal
 *
 */
public class IsAccessibleFromMoreThanOneModule {

    public static void main(String[] args) {

        // System.get

        doTest();

    }

    public static String doTest() {
        IsAccessibleFromMoreThanOneModule c = new IsAccessibleFromMoreThanOneModule();
        // ClassLoader classLoader = c.getClass().getClassLoader();
        // ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

        Enumeration<URL> e;
        try {

            String classe = "net/java/xades/security/xml/XMLSignatureElement.class";
            // "org/w3c/dom/Text.class";
            // "org/w3c/dom/Document.class";
            // "org/w3c/dom/NodeList.class"

            e = classLoader.getResources(classe);

            StringBuffer str = new StringBuffer();

            while (e.hasMoreElements()) {

                URL u = e.nextElement();

                str.append(u.toString()).append("\n");
            }

            return str.toString();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "Error: " + e1.getMessage();
        }

    }

}
