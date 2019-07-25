package org.fundaciobit.plugins.signature.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 
 * @author anadal
 *
 */
public class CommonInfoSignature {

  String languageUI;

  String filtreCertificats;

  String username;
  
  String administrationID;

  boolean alwaysCreateRevision = true;

  /**
   * 
   */
  public CommonInfoSignature() {
  }

  /**
   *
   */
  public CommonInfoSignature(String languageUI, String filtreCertificats, String username,
                             String administrationID, boolean alwaysCreateRevision) {
    super();
    this.languageUI = languageUI;
    this.filtreCertificats = filtreCertificats;
    this.username = username;
    this.administrationID = administrationID;
    this.alwaysCreateRevision = alwaysCreateRevision;
  }


  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public String getFiltreCertificats() {
    return filtreCertificats;
  }

  public void setFiltreCertificats(String filtreCertificats) {
    this.filtreCertificats = filtreCertificats;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAdministrationID() {
    return administrationID;
  }

  public void setAdministrationID(String administrationID) {
    this.administrationID = administrationID;
  }

  public boolean isAlwaysCreateRevision() {
    return alwaysCreateRevision;
  }

  public void setAlwaysCreateRevision(boolean alwaysCreateRevision) {
    this.alwaysCreateRevision = alwaysCreateRevision;
  }

  public static String cleanFiltreCertificats(String certificateFilter) {
    StringBuffer cleanFilter = new StringBuffer();
  
    if (certificateFilter != null && certificateFilter.trim().length() != 0) {
          
        BufferedReader br = new BufferedReader(new StringReader(certificateFilter));
        String line;
        try {
          while ((line = br.readLine()) != null) {
            line = line.trim();
            
            if(line.startsWith("filter=") || line.startsWith("filters=") 
                || line.startsWith("filters.")) {
              int i = line.indexOf('=');
              if (i != -1) {
                cleanFilter.append(line).append("\n");
              };
            }
            
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        
    }
    return cleanFilter.toString();
  }
}
