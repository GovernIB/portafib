package org.fundaciobit.apisib.apifirmasimple.examplewar.v1.utils;

import java.util.Properties;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaWebSimpleJersey;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaWebCache {

  public static final String BASE_PACKAGE = "org.fundaciobit.apifirmasimple.exemple.apifirmaweb.";

  public static ApiFirmaWebSimple getApiFirmaWebSimple() throws Exception {

    Properties prop = System.getProperties();

    // XYZ ZZZ Check NULL POINTER en valors
    
    return new ApiFirmaWebSimpleJersey(prop.getProperty(BASE_PACKAGE + "endpoint"),
        prop.getProperty(BASE_PACKAGE + "username"), prop.getProperty(BASE_PACKAGE
            + "password"));

  }

}
