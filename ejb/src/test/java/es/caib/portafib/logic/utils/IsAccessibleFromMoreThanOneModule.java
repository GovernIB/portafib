package es.caib.portafib.logic.utils;

import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;


/**
 * XYZ ZZZ Passar a Tests
 * @author anadal
 *
 */
public class IsAccessibleFromMoreThanOneModule {

    
    
    public static void main(String[] args) {
        
        
        
        IsAccessibleFromMoreThanOneModule c = new IsAccessibleFromMoreThanOneModule();
        
        
        Enumeration<URL> e;
        try {
            
            String classe = "org/w3c/dom/Document.class";//"org/w3c/dom/Element.class"; //"org/w3c/dom/Text.class"; //  // "org/w3c/dom/NodeList.class"
            
            e =  c.getClass().getClassLoader().getResources(classe);
            
            
            Collections.list(e).forEach(System.out::println);
            
            
            es.caib.portafib.logic.signatures.XadesSignatureExtractor xse = new es.caib.portafib.logic.signatures.XadesSignatureExtractor();
            
            xse.extract(null);
            
        } catch (Throwable e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       

        

        
        
    }
    
    
}
