package es.caib.portafib.applet.signers;

import java.security.cert.X509Certificate;
import java.util.List;

import org.fundaciobit.plugins.utils.CertificateUtils;

import es.gob.afirma.keystores.filters.CertificateFilter;
import es.gob.afirma.signers.pades.PAdESTriPhaseSigner;
import es.gob.afirma.signers.pades.PdfPreProcessor;


/**
 * 
 * @author anadal
 *  Código de filtro:
 *  if (filterValue.startsWith(FILTER_TYPE_POLICYID)) {
          
          String idsStr = filterValue.substring(FILTER_TYPE_POLICYID.length());
          
          String[] ids = idsStr.split(",");
          
          if (ids != null && ids.length != 0) {
            
            List<String> oidsAllowed = new ArrayList<String>();
            for (String oid : ids) {
              oid = oid.trim();
              if (oid.length() != 0) {
                oidsAllowed.add(oid);
              }
            }
            filter = new PolicyIdFilter(oidsAllowed);
          }
        }
 *
 *
 *
 */
public class PolicyIdFilter extends CertificateFilter {

  
  final List<String> oidsAllowed;
  
  
  public PolicyIdFilter(List<String> oidsAllowed) {
    this.oidsAllowed = oidsAllowed;
  }
  
  
  @Override
  public boolean matches(X509Certificate cert) {
    try {
      String oid = CertificateUtils.getCertificatePolicyId(cert);
      if (oid == null) {
        System.out.println("No puc trobar la politica OID de " + cert.toString());
        return false;
      }
      
      return oidsAllowed.contains(oid);
    } catch (Exception e) {        
      e.printStackTrace();
    }
    return false;
  }
  
  
  
  
}
