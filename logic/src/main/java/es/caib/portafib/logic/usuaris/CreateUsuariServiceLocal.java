package es.caib.portafib.logic.usuaris;

import es.caib.portafib.jpa.UsuariEntitatJPA;

public interface CreateUsuariServiceLocal {

    String JNDI_NAME = "portafib/CreateUsuariServiceEJB/local";

    UsuariEntitatJPA getOrCreateByUsername(String username, String entitatId);

    UsuariEntitatJPA getOrCreateByAdministrationId(String administrationId, String entitatId);

}
