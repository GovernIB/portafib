package es.caib.portafib.logic.utils;

import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.model.entity.PerfilDeFirma;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PerfilConfiguracioDeFirma {

  public final PerfilDeFirma perfilDeFirma;

  public final UsuariAplicacioConfiguracioJPA configuracioDeFirma;

  public PerfilConfiguracioDeFirma(PerfilDeFirma perfilDeFirma,
      UsuariAplicacioConfiguracioJPA configuracioDeFirma) {
    super();
    this.perfilDeFirma = perfilDeFirma;
    this.configuracioDeFirma = configuracioDeFirma;
  }

}
