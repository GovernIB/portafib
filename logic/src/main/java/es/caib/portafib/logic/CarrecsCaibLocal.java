package es.caib.portafib.logic;


import es.caib.portafib.model.entity.UsuariEntitat;

import javax.ejb.Local;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface CarrecsCaibLocal {

  public UsuariEntitat processarCarrecsCaib(String tipus,
      String codusu, String nomrol,String valordomini, String agentsql, String nom);

}

