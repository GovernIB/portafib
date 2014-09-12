package es.caib.portafib.ws.test.v1;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class PeticioInfo {

  public String title;
  public String description;
  public boolean signAnnexes; // S'han de firmar el anexos ??
  public String senderUserName;
  public String senderUserEmail;
  public Calendar dateLimit;
  public String importance;
  public String signMode;
  public int signTipus;
  public String subject;
  public boolean checkCert;
  public Map<Integer, String[]> signersIdsByBloc = new HashMap<Integer, String[]>();
  public Map<Integer, Integer> minSignersByBloc = new HashMap<Integer, Integer>();

  public String externalData;


  public DocInfo docToSign;
  
  public DocInfo[] annexos = new DocInfo[0];
  
  
  
}
