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
  
  public static final String JNDI_NAME = "portafib/AgentsCAIBEJB/local";

  public UsuariEntitat processarCarrecCAIB(String tipus,
      String codusu, String nomrol,String valordomini, String agentsql, String nom);
  
  
  public UsuariEntitat processarUsuariCAIB(String tipus,
      String codusu, String agentsql, String password);

}

