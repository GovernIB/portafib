package es.caib.portafib.back.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.utils.Configuracio;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;


/**
 * 
 * @author anadal
 * 
 */
public class Utils {

  protected static final Logger log = Logger.getLogger(Utils.class);

  public static String getSortIcons(BaseFilterForm baseFilter, Field<?> theField) throws Exception {
    
    
    if (baseFilter == null) {
      throw new Exception("FilterForm is null.");
    }
    
    String field = theField.getJavaName();
    String code = theField.getFullName();
    String html;
  
    String newCode = baseFilter.getLabels().get(theField);
    if (newCode != null) {
      code = newCode;
    }
    
    if (baseFilter.isVisibleOrderBy()) {

      if (field.equals(baseFilter.getOrderBy())) {
  
        html = "<span style=\"cursor:row-resize\" onClick=\"javascript:executeOrderBy('"
            + field
            + "', "
            + !baseFilter.isOrderAsc()
            + ");\""
            + " title=\""
            + I18NUtils
                .tradueix(!baseFilter.isOrderAsc() ? "genapp.form.sort.asc" : "genapp.form.sort.desc")
            + "\" >" + I18NUtils.tradueix(code) + "<i class=\""
            + (baseFilter.isOrderAsc() ? "icon-chevron-up" : "icon-chevron-down")
            + "\"></i></span>";
      } else {
        html = "<span style=\"cursor:row-resize\" onclick=\"javascript:executeOrderBy('" + field + "', true);\" "
            + " title=\"" + I18NUtils.tradueix("genapp.form.sort.asc") + "\">"
            + I18NUtils.tradueix(code) + "<i class=\"icon-resize-vertical\"></i></span>";
      }
    } else {
      html = I18NUtils.tradueix(code);
    }
    
    return html;
  }

  public static String intArrayToString(int[] itemsPerPagines) {
    String str = Arrays.toString(itemsPerPagines);
    return str.substring(1, str.length() - 1).replace(" ", "");
  }

  /*
   * Funció que retorna el correu de l'usuari loguejat. Primer mira si te
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
  
  public static AppletConfig getAppletConfig(EntitatJPA entitat, 
      String languageUI, String contextWeb) {
    AppletConfig config;
    if (entitat.getPolicyIdentifier() != null) {
      config = new AppletConfig(languageUI,
          contextWeb, entitat.getFiltreCertificats(),
          Configuracio.getAppletSignerClass(), null,
        entitat.getPolicyIdentifier(), entitat.getPolicyIdentifierHash(),
        entitat.getPolicyIdentifierHashAlgorithm(), entitat.getPolicyUrlDocument());
    } else {
      config = new AppletConfig(languageUI,
          contextWeb, entitat.getFiltreCertificats(),
          Configuracio.getAppletSignerClass(), null);
    }
    return config;
  }
  
  
  public static String getFirmatPerFormat(EntitatJPA entitat, String lang) {
    
    // TODO en un futur el format estirà dins l'entitat
    
    String firmatPerFormat = Configuracio.getFirmatPerFormat(entitat.getEntitatID(), lang); 
    
    if (firmatPerFormat == null) {
      // {0} {1,choice,0#|1< - NIF {2}} {4,choice,0#|1< - Càrrec {5}} (Emissor {3})
      firmatPerFormat = I18NUtils.tradueix("firmatperformat");
    }
    
    return firmatPerFormat;

  }
  
  
  public static String getMotiuDeFirmaFormat(EntitatJPA entitat, String lang) {
    
    // TODO en un futur el format estirà dins l'entitat
    
    String firmatPerFormat = Configuracio.getMotiuDeFirmaFormat(entitat.getEntitatID(), lang); 
    
    if (firmatPerFormat == null) {
      // {0} {1,choice,0#|1< - NIF {2}} {4,choice,0#|1< - Càrrec {5}} (Emissor {3})
      firmatPerFormat = I18NUtils.tradueix("motiudelegacio");
    }
    
    return firmatPerFormat;

  }
  
}
