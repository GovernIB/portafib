package es.caib.portafib.ws.utils;


import es.caib.portafib.jpa.UsuariAplicacioJPA;

/**
 * 
 * @author anadal
 * 
 */
public class UsuariAplicacioCache {


  private static final ThreadLocal<UsuariAplicacioJPA> appInfo = new ThreadLocal<UsuariAplicacioJPA>();

  public static void put(UsuariAplicacioJPA appname) {
    appInfo.set(appname);
  }

  public static UsuariAplicacioJPA get() {
    return appInfo.get();
  }

  public static void remove() {
    appInfo.remove();
  }

}
