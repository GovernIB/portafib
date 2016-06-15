package es.caib.portafib.back.controller.aden;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.GenerarFiltreCaibForm;

@Controller
@RequestMapping(value = "/aden/generarfiltrecaib")
@SessionAttributes(types = { GenerarFiltreCaibForm.class })
public class GeneradorDeFiltreCaibController {

  public String getTileForm() {
    return "generarFiltreCaib";
  }

  protected final Logger log = Logger.getLogger(getClass());

  /**
   * Carregar el formulari per un nou TipusEstatDeFirmaFinal
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView generarFiltreCaibGet(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    GenerarFiltreCaibForm gfcf = new GenerarFiltreCaibForm();

    gfcf.setUrl("http://www.caib.es/signaturacaib/signatura_api.properties");

    ModelAndView mav = new ModelAndView(getTileForm());
    mav.addObject("generarFiltreCaibForm", gfcf);

    return mav;
  }

  /**
   * Guardar un nou TipusEstatDeFirmaFinal
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String generarFiltreCaibPost(@ModelAttribute GenerarFiltreCaibForm gfcf,
      BindingResult result, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    String urlStr = gfcf.getUrl();

    if (urlStr == null || urlStr.trim().length() == 0) {
      // TODO Traduir
      HtmlUtils.saveMessageError(request,
          "Ha d´introduir una adreça al fitxer signatura_api.properties");
      return getTileForm();
    }

    try {

      String filtre = getFiltre(urlStr);

      gfcf.setFiltre(filtre);

      return getTileForm();
      // return "redirect:" + getContextWeb() + "/list/1";

    } catch (Throwable __e) {
      log.error(__e.getMessage(), __e);

      HtmlUtils.saveMessageError(request, "Error generant el filtre: " + __e.getMessage());
      gfcf.setFiltre(null);
      return getTileForm();
    }

  }


  public static String getFiltre(String urlStr) throws Exception {
    
    
    URL url = new URL(urlStr);
    InputStream is = url.openStream();

    Properties prop = new Properties();

    prop.load(is);

    StringBuffer filter = new StringBuffer();
    
    
    int count = 0;
    count++;
    filter.append("filters." + count+ "=nonexpired:;");
    

    // --- CA
    {
      String baseKey = "ca";
      List<String> list = getValuesWithBasekey(prop, baseKey);
  
      StringBuffer filterCA = new StringBuffer();

      if (!list.isEmpty()) {
        //count++;
  
        //filterCA.append("filters." + count+ "=issuer.rfc2254.recurse:|");
        filterCA.append("issuer.rfc2254.recurse:|");
  
        for (String ca : list) {
          filterCA.append("(CN=" + ca + ")(OU=" + ca + ")");
        }
  
        filterCA.append(";");
  
        filter.append(filterCA.toString());
  
      }
    }

    // --- Policies
    {
      String[] policiesBasekey = new String[] { "policy.advanced", "policy.recognized",
          "policy.recognizedsecuredevice", "policy.publicemployeeOID", };
  
      StringBuffer filterPolicies = new StringBuffer();
      for (int i = 0; i < policiesBasekey.length; i++) {
        List<String> list = getValuesWithBasekey(prop, policiesBasekey[i]);
        if (!list.isEmpty()) {
          for (String pol : list) {
            filterPolicies.append(pol).append(",");
          }
        }
      }
  
      if (filterPolicies.length() != 0) {
        //count++;
        // filter.append("filters." + count+ "=policyid:");
        filter.append("policyid:");
        String filterPoliciesStr = filterPolicies.toString();
        // Llevam la darrera coma
        filter.append(filterPoliciesStr.substring(0, filterPoliciesStr.length() - 1));
        filter.append(";");
      }
    }

    return filter.toString();

  }

  public static List<String> getValuesWithBasekey(Properties prop, String baseKey) {
    List<String> list = new ArrayList<String>();
    int count = 1;
    String value;
    do {
      value = prop.getProperty(baseKey + "." + count);
      if (value == null) {
        break;
      } else {
        count++;
        list.add(value);
      }

    } while (true);
    return list;
  }

}
