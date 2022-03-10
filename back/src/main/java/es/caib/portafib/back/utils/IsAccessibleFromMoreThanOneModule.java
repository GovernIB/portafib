package es.caib.portafib.back.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 
 * @author anadal
 *
 */
public class IsAccessibleFromMoreThanOneModule {

    
    
    public static void main(String[] args) {
        
        doTest();
        
    }
        
        
    public static String doTest() {   
        IsAccessibleFromMoreThanOneModule c = new IsAccessibleFromMoreThanOneModule();
        
        
        Enumeration<URL> e;
        try {
            
            String classe = "org/w3c/dom/Element.class"; //"org/w3c/dom/Text.class"; // "org/w3c/dom/Document.class"; // "org/w3c/dom/NodeList.class"
            
            e = c.getClass().getClassLoader().getResources(classe);
            
            StringBuffer str = new StringBuffer();
            
            while(e.hasMoreElements()) {
                
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
