package es.caib.portafib.applet;

import java.text.MessageFormat;
import java.util.PropertyResourceBundle;

/**
 * 
 * @author anadal
 *
 */
public class I18NUtils {

  
  public static String traduiex(PropertyResourceBundle bundle,
      String code, String... params) {
    String msg;
    try {
      msg = bundle.getString(code);
    } catch (java.util.MissingResourceException mre) {
      mre.printStackTrace(System.err);
      msg = code;
    }
    MessageFormat form = new MessageFormat(msg);
    return form.format(params);
  }
}
