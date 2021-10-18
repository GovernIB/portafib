package es.caib.portafib.logic;


import es.caib.portafib.model.entity.UsuariEntitat;

import javax.ejb.Local;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface AgentsCAIBLocal {

  String JNDI_NAME = "java:app/portafib-ejb/AgentsCAIBEJB";

  public UsuariEntitat processarCarrecCAIB(String tipus,
      String codusu, String nomrol,String valordomini, String agentsql, String nom);
  
  
  public UsuariEntitat processarUsuariCAIB(String tipus,
      String codusu, String agentsql, String password);

}

