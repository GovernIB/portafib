package es.caib.portafib.logic.usuaris;

import es.caib.portafib.persistence.UsuariEntitatJPA;

public interface CreateUsuariServiceLocal {

    String JNDI_NAME = "java:app/portafib-ejb/CreateUsuariServiceEJB";

    UsuariEntitatJPA getOrCreateByUsername(String username, String entitatId);

    UsuariEntitatJPA getOrCreateByAdministrationId(String administrationId, String entitatId);

}
