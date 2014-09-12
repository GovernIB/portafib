package es.caib.portafib.back.preparer;

import java.util.List;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.springframework.stereotype.Component;

import es.caib.portafib.ejb.IdiomaLocal;
import es.caib.portafib.model.entity.Idioma;
import es.caib.portafib.model.fields.IdiomaFields;

/**
 * @author GenApp
 *
 */
@RunAs("PFI_USER")
@Component
public class CapPreparer extends ViewPreparerSupport implements IdiomaFields {
  
  protected final Logger log = Logger.getLogger(getClass());
  
  @EJB(mappedName = "portafib/IdiomaEJB/local")
  private IdiomaLocal idiomaEjb;

	@Override
	public void execute(TilesRequestContext tilesContext, 
	    AttributeContext attributeContext) throws PreparerException {

	  Map<String, Object> request = tilesContext.getRequestScope();
    /*
	  LoginInfo loginInfo = LoginInfo.getInstance();
	  
	  if (loginInfo.getEntitatID() != null) {

  	  String nom = loginInfo.getEntitat().getNom();
      tilesContext.getRequestScope().put("entitat_nom", nom);
      attributeContext.putAttribute("entitat_nom", new Attribute(nom));

      FitxerJPA logocap = loginInfo.getEntitat().getLogoWeb();
      tilesContext.getRequestScope().put("entitat_logo_cap", logocap);
      attributeContext.putAttribute("entitat_logo_cap", new Attribute(logocap));
	  }
    */
    // Idiomes (cache)
    try {

      Object idiomesAttribute = tilesContext.getSessionScope().get("idiomes");
      

      List<Idioma> idiomes;
      if (idiomesAttribute != null && idiomesAttribute instanceof List) {
        idiomes = (List<Idioma>)idiomesAttribute;
      } else {
        idiomes = idiomaEjb.select(SUPORTAT.equal(true), new OrderBy(ORDRE));
        tilesContext.getSessionScope().put("idiomes", idiomes);
      }

      request.put("idiomes", idiomes);

    } catch (I18NException e) {
      // TODO: handle exception
      log.error("Error cercant idiomes suportats.", e);
    }

	}
}
