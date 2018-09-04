package org.fundaciobit.pluginsib.signature.firmasimple.firmasimpleexemple.utils;

import java.util.Properties;

import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.ApiFirmaEnServidorSimple;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaEnServidorCache {

  public static final String BASE_PACKAGE = "org.fundaciobit.apifirmasimple.exemple.apifirmaenservidor.";

  public static ApiFirmaEnServidorSimple getApiFirmaEnServidorSimple() throws Exception {

    Properties prop = System.getProperties();
    
    // XYZ ZZZ Check NULL POINTER en valors

    return new ApiFirmaEnServidorSimple(prop.getProperty(BASE_PACKAGE + "endpoint"),
        prop.getProperty(BASE_PACKAGE + "username"), prop.getProperty(BASE_PACKAGE
            + "password"));

  }

}
