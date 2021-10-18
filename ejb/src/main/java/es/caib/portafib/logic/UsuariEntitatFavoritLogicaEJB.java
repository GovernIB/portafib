package es.caib.portafib.logic;


import es.caib.portafib.ejb.UsuariEntitatFavoritEJB;
import es.caib.portafib.model.fields.UsuariEntitatFavoritFields;
import javax.ejb.Stateless;


/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = "UsuariEntitatFavoritLogicaEJB")
public class UsuariEntitatFavoritLogicaEJB extends UsuariEntitatFavoritEJB implements
    UsuariEntitatFavoritLogicaLocal, UsuariEntitatFavoritFields {

}
