package es.caib.portafib.back.preparer;

import javax.annotation.security.RunAs;

import org.apache.log4j.Logger;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.stereotype.Component;

/**
 * @author GenApp
 * @author anadal
 *
 */
@RunAs("PFI_USER")
@Component
public class PeuPreparer extends ViewPreparerSupport {
  
  protected final Logger log = Logger.getLogger(getClass());

	@Override
	public void execute(TilesRequestContext tilesContext, AttributeContext attributeContext) throws PreparerException {
	}
}
