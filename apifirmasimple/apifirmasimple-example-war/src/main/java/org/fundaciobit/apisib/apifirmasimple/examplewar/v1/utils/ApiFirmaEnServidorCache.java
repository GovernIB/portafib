package org.fundaciobit.apisib.apifirmasimple.examplewar.v1.utils;

import java.util.Properties;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaEnServidorSimpleJersey;

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

    return new ApiFirmaEnServidorSimpleJersey(prop.getProperty(BASE_PACKAGE + "endpoint"),
        prop.getProperty(BASE_PACKAGE + "username"), prop.getProperty(BASE_PACKAGE
            + "password"));

  }

}
