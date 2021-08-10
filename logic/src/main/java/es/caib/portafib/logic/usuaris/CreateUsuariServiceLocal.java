package es.caib.portafib.logic.usuaris;

import es.caib.portafib.jpa.UsuariEntitatJPA;

public interface CreateUsuariServiceLocal {

    String JNDI_NAME = "java:app/portafib-logic/CreateUsuariServiceEJB";

    UsuariEntitatJPA getOrCreateByUsername(String username, String entitatId);

    UsuariEntitatJPA getOrCreateByAdministrationId(String administrationId, String entitatId);

}
