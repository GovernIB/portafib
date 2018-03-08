package org.fundaciobit.apifirmasimple.exemple.utils;

import java.util.Properties;

import org.fundaciobit.apifirmasimple.v1.ApiFirmaWebSimple;

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
    
    return new ApiFirmaWebSimple(prop.getProperty(BASE_PACKAGE + "endpoint"),
        prop.getProperty(BASE_PACKAGE + "username"), prop.getProperty(BASE_PACKAGE
            + "password"));

  }

}
