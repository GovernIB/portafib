package es.caib.portafib.logic;

import es.caib.portafib.model.entity.UsuariEntitat;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface AgentsCAIBLocal {

    String JNDI_NAME = "java:app/portafib-ejb/AgentsCAIBEJB";

    public UsuariEntitat processarCarrecCAIB(String tipus, String codusu, String nomrol, String valordomini,
            String agentsql, String nom);

    public UsuariEntitat processarUsuariCAIB(String tipus, String codusu, String agentsql, String password);

    public List<String> enviarCorreuAdmistradors(String subject, String message, String entitatID) throws I18NException;

}
