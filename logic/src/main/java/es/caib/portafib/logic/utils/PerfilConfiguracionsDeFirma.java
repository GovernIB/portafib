package es.caib.portafib.logic.utils;

import java.util.Map;

import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.model.entity.PerfilDeFirma;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PerfilConfiguracionsDeFirma {

  public final PerfilDeFirma perfilDeFirma;
  public final Map<String, UsuariAplicacioConfiguracioJPA> configBySignID;

  public PerfilConfiguracionsDeFirma(PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) {
    super();
    this.perfilDeFirma = perfilDeFirma;
    this.configBySignID = configBySignID;
  }

}
