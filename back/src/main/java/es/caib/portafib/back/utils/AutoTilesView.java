package es.caib.portafib.back.utils;

import org.springframework.web.servlet.view.tiles3.TilesView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Fundacio Bit
 * 
 * @author anadal Date: 4/10/12
 */
public class AutoTilesView extends TilesView {

  @Override
  protected void renderMergedOutputModel(Map<String, Object> model,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    super.renderMergedOutputModel(model, request, response);
  }
}
