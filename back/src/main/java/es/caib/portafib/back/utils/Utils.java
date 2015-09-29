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
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;

/**
 * 
 * @author anadal
 * 
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
    
    // TODO en un futur el format estir� dins l'entitat
    
    String firmatPerFormat = Configuracio.getFirmatPerFormat(entitat.getEntitatID(), lang); 
    
    if (firmatPerFormat == null) {
      // {0} {1,choice,0#|1< - NIF {2}} {4,choice,0#|1< - C�rrec {5}} (Emissor {3})
      firmatPerFormat = I18NUtils.tradueix("firmatperformat");
    }
    
    return firmatPerFormat;

  }
  
  
  public static String getMotiuDeFirmaFormat(EntitatJPA entitat, String lang) {
    
    // TODO en un futur el format estir� dins l'entitat
    
    String firmatPerFormat = Configuracio.getMotiuDeFirmaFormat(entitat.getEntitatID(), lang); 
    
    if (firmatPerFormat == null) {
      // {0} {1,choice,0#|1< - NIF {2}} {4,choice,0#|1< - C�rrec {5}} (Emissor {3})
      firmatPerFormat = I18NUtils.tradueix("motiudelegacio");
    }
    
    return firmatPerFormat;

  }

}
