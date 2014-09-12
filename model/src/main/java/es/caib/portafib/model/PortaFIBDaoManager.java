package es.caib.portafib.model;

public class PortaFIBDaoManager {
  
  private static IPortaFIBDaoManagers instance = null;
  
  public static void setDaoManagers(IPortaFIBDaoManagers managers) {
    instance = managers;
  }
  
  public static IPortaFIBDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode PortaFIBDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
