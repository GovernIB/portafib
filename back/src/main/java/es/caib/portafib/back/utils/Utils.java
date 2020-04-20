package es.caib.portafib.back.utils;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.model.entity.UsuariPersona;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.genapp.common.web.form.BaseForm;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static es.caib.portafib.utils.ConstantsV2.FILENAME_UNMAPPABLE_REPLACEMENT;
import static es.caib.portafib.utils.ConstantsV2.ISO_8859_1;

/**
 * 
 * @author anadal
 * @author areus
 */
public class Utils {

  protected static final Logger log = Logger.getLogger(Utils.class);

  public static String getSortIconsAdditionalField(BaseFilterForm baseFilter,
       AdditionalField<?, ?> additionalField) throws Exception {
      
      String code = additionalField.getCodeName();
      String newCode = baseFilter.getLabels().get(additionalField.getValueField());
      if (newCode != null) {
        code = newCode;
      }
      return getSortIcons(baseFilter, additionalField.getOrderBy(), code);
  }
  

  public static String getSortIcons(BaseFilterForm baseFilter, Field<?> theField) throws Exception {
    String code = theField.getFullName();
    String newCode = baseFilter.getLabels().get(theField);
    if (newCode != null) {
      code = newCode;
    }
    return getSortIcons(baseFilter, theField, code);
  }
  
  
  protected static String getSortIcons(BaseFilterForm baseFilter, 
      Field<?> theField, String code) throws Exception {
    
    if (baseFilter == null) {
      throw new Exception("FilterForm is null.");
    }

    
    if (baseFilter.isVisibleOrderBy() && theField != null) {

      String field = theField.getJavaName();
      
      String html;
      
      boolean orderedBythisfield = false;
      boolean isOrderedAsc = false;
      if (baseFilter.getOrderBy() == null) {
        // cercam en el valors per defecte
        OrderBy[] orderByDefaultList = baseFilter.getDefaultOrderBy();
        if (orderByDefaultList != null) {
          for (OrderBy orderBy : orderByDefaultList) {          
            if (orderBy.javaName.equals(theField.fullName)) {
              orderedBythisfield = true;
              isOrderedAsc = orderBy.orderType.equals(OrderType.ASC);
              break;
            }
          }
        }
        
      } else {
        orderedBythisfield = field.equals(baseFilter.getOrderBy());
        isOrderedAsc = baseFilter.isOrderAsc();
      }
      
      
      if (orderedBythisfield) {
  
        html = "<span style=\"cursor:row-resize\" onClick=\"javascript:executeOrderBy('"
            + field
            + "', "
            + !isOrderedAsc
            + ");\""
            + " title=\""
            + I18NUtils
                .tradueix(!isOrderedAsc ? "genapp.form.sort.asc" : "genapp.form.sort.desc")
            + "\" >" + getText(code) + "<i class=\""
            + (isOrderedAsc ? "icon-chevron-up" : "icon-chevron-down")
            + "\"></i></span>";
      } else {
        html = "<span style=\"cursor:row-resize\" onclick=\"javascript:executeOrderBy('" + field + "', true);\" "
            + " title=\"" + I18NUtils.tradueix("genapp.form.sort.asc") + "\">"
            + getText(code) + "<i class=\"icon-resize-vertical\"></i></span>";
      }
      return html;
    } else {
      return getText(code);
    }

  }
  

  private static String getText(String code) {
    if (code == null) {
      return null;
    }
    if (code.startsWith("=")) {
      return code.substring(1);
    } else {
      return I18NUtils.tradueix(code);
    }
    
  }
  

  public static String intArrayToString(int[] itemsPerPagines) {
    String str = Arrays.toString(itemsPerPagines);
    return str.substring(1, str.length() - 1).replace(" ", "");
  }

  /*
   * Funci� que retorna el correu de l'usuari loguejat. Primer mira si te
   * definit el de l'usuariEntitat i si no agafa el de l'usuariPersona.
   */
  public static String getLoggedUserEmail() {
    LoginInfo loginInfo = LoginInfo.getInstance();

    UsuariEntitatJPA usuariEntitat = loginInfo.getUsuariEntitat();
    if (usuariEntitat != null) {
      String email = usuariEntitat.getEmail();
      if (email != null) {
        return email;
      }
    }
    return loginInfo.getUsuariPersona().getEmail();
  }

  
  public static final Comparator<StringKeyValue> STRINGKEYVALUECOMPARATOR = new Comparator<StringKeyValue>() {
    @Override
    public int compare(StringKeyValue o1, StringKeyValue o2) {
      return o1.value.compareToIgnoreCase(o2.value);
    }
  };
  
  public static List<StringKeyValue> usuariEntitatList2StringKeyValue(
      List<UsuariEntitatJPA> users) {
    List<StringKeyValue> listNovaFirma = new ArrayList<StringKeyValue>();
    for (UsuariEntitatJPA usuariEntitat : users) {
      addUserToList(listNovaFirma, usuariEntitat);
    }    
    sortStringKeyValueList(listNovaFirma);    
    return listNovaFirma;
  }

  public static List<StringKeyValue> sortStringKeyValueList(List<StringKeyValue> listNovaFirma) {
    Collections.sort(listNovaFirma, STRINGKEYVALUECOMPARATOR);
    return listNovaFirma;
  }

  public static void addUserToList(List<StringKeyValue> listNovaFirma,
      UsuariEntitatJPA usuariEntitat) {
    String carrec = usuariEntitat.getCarrec();
    if (carrec == null) {
      listNovaFirma.add(new StringKeyValue(usuariEntitat.getUsuariEntitatID(),
          getNom(usuariEntitat.getUsuariPersona())));
    } else {
      listNovaFirma.add(new StringKeyValue(usuariEntitat.getUsuariEntitatID(), "(*) "
          + usuariEntitat.getCarrec()));
    }
  }
  
  public static String getNom(UsuariPersona up) {
    return up.getNom() + " " + up.getLlinatges() + " (" + up.getNif() + ")";
  }

  public static String getLlinatgeNom(UsuariPersona up) {
    return up.getLlinatges() + ", " + up.getNom() + " (" + up.getNif() + ")";
    
  }

  public static String getOnlyNom(UsuariPersona up) {
    return up.getNom() + " " + up.getLlinatges();
  }
  
  
  public static String processEmailURL(String addicionalInfo) {
    
    if (addicionalInfo == null) {
      return null;
    }

    addicionalInfo = addicionalInfo.trim();
    if (addicionalInfo.length() == 0) {
      return "";
    }

    java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    if (p.matcher(addicionalInfo).matches()) {
      return "<a href=\"mailto:" + addicionalInfo + "\">" + addicionalInfo + "</a>";
    } else {
      
      String url = addicionalInfo.toLowerCase();
      
      if (url.startsWith("http://") || url.startsWith("https://")) {
        return "<a href=\""+ addicionalInfo +"\" target=\"_blank\">" + addicionalInfo + "</a>";
      } else {
        return addicionalInfo;
      }
    }

  }
  
 
  
  public static void printRequestInfo(HttpServletRequest request) {
    log.info(" +++++++++++++++++ PRINT REQUEST INFO ++++++++++++++++++++++");
    log.info(" ++++ Scheme: " + request.getScheme());
    log.info(" ++++ ServerName: " + request.getServerName());
    log.info(" ++++ ServerPort: " + request.getServerPort());

    log.info(" ++++ PathInfo: " + request.getPathInfo());
    log.info(" ++++ PathTrans: " + request.getPathTranslated());
    log.info(" ++++ ContextPath: " + request.getContextPath());
    log.info(" ++++ ServletPath: " + request.getServletPath());
    
    log.info(" ++++ getRequestURI: " + request.getRequestURI());
    log.info(" ++++ getRequestURL: " + request.getRequestURL());
    log.info(" ++++ getQueryString: " + request.getQueryString());
    
    log.info(" ++++ javax.servlet.forward.request_uri: " + 
        (String) request.getAttribute("javax.servlet.forward.request_uri"));
    
    log.info(" ===============================================================");
  }
  
  
  public static String getUrlBaseFromFullUrl(HttpServletRequest request, String baseUrlFull) {
    URL url;
    try {
      url = new URL(baseUrlFull);
    } catch (MalformedURLException e) {
      e.printStackTrace();        
      return null;
    }

    String port;
    if (url.getPort() == -1) {
      port = "";
    } else {
      port = ":" + url.getPort();
    }

    String baseUrl = url.getProtocol() + "://" + url.getHost() + port
        + request.getContextPath();
    return baseUrl;
  }
  
  /**
   * HidddenNullFields
   */
  public static void hiddenEmptyFields(BaseForm form, Object obj, Field<?>[]  allFields) {
    
    Class<?> objClass = obj.getClass();
    for (Field<?> field : allFields) {
      try {
        java.lang.reflect.Field fieldJava = objClass.getDeclaredField(field.javaName);    
        fieldJava.setAccessible(true);
        if (fieldJava.get(obj) == null) {
          form.addHiddenField(field);
        };
      } catch (Exception e) {
        log.error("Error extraient valors de camps: " + e.getMessage(), e);
      }
    }
  }

  /**
   * Retorna el contingut de la capçalera <i>Content-Disposition</i> que compleix
   * amb https://tools.ietf.org/html/rfc6266#section-5
   * @param attachment si és true empra attachment, sinó inline
   * @param filename nom del fitxer suggerit
   * @return contingut de la capçalera.
   * @throws Exception
   */
  public static String getContentDispositionHeader(boolean attachment, String filename)
          throws Exception {

    // Preparam el nom suggerit en UTF-8
    // No podem emprar URLEncoder.encode perquè només és per paràmetres http i
    // converteix els espais a "+" enlloc de a "%20"
    String utf8filename = UriUtils.encodePath(filename, "UTF-8");

    // Asseguram que el filename suggerit en ISO-8859-1 no té caràcters incompatibles
    CharsetEncoder charsetEncoder = ISO_8859_1.newEncoder();
    if (!charsetEncoder.canEncode(filename)) {
      charsetEncoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
      charsetEncoder.replaceWith(FILENAME_UNMAPPABLE_REPLACEMENT );
      ByteBuffer buffer = charsetEncoder.encode(CharBuffer.wrap(filename));
      filename = new String(buffer.array(), ISO_8859_1);
    }

    // Implementació de RFC6266. La majoria de navegadors soporten la codificació en UTF-8 emprant
    // "filename*=", pels que no ho soportin, ficam "filename=" abans.
    return (attachment ? "attachment" : "inline")
            + "; filename=\"" + filename + "\""
            + "; filename*=UTF-8''" + utf8filename;
  }

}
