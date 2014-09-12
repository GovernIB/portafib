package es.caib.portafib.logic;


import es.caib.portafib.ejb.UsuariEntitatFavoritEJB;
import es.caib.portafib.model.fields.UsuariEntitatFavoritFields;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = "UsuariEntitatFavoritLogicaEJB")
@SecurityDomain("seycon")
public class UsuariEntitatFavoritLogicaEJB extends UsuariEntitatFavoritEJB implements
    UsuariEntitatFavoritLogicaLocal, UsuariEntitatFavoritFields {

}
