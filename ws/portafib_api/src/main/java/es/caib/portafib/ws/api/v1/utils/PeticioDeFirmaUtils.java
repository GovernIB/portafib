package es.caib.portafib.ws.api.v1.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.ws.api.v1.AnnexBean;
import es.caib.portafib.ws.api.v1.BlocDeFirmesWs;
import es.caib.portafib.ws.api.v1.FirmaBean;
import es.caib.portafib.ws.api.v1.FitxerBean;
import es.caib.portafib.ws.api.v1.FluxDeFirmesWs;
import es.caib.portafib.ws.api.v1.PeticioDeFirmaWs;
import es.caib.portafib.ws.api.v1.PortaFIBUsuariEntitatWs;
import es.caib.portafib.ws.api.v1.WsI18NException;

/**
 * 
 * @author anadal
 *
 */
public class PeticioDeFirmaUtils {
  
  
  public static PeticioDeFirmaWs constructPeticioDeFirma(PortaFIBUsuariEntitatWs usuariEntitatAPI,
      String titol, String remitent, FitxerBean fitxerAFirmar, String[] nifs) 
      throws WsI18NException {
    
    return constructPeticioDeFirma(usuariEntitatAPI,
        titol, remitent, fitxerAFirmar, nifsInBlock(nifs));
    
  }
  
    
  public static PeticioDeFirmaWs constructPeticioDeFirma(PortaFIBUsuariEntitatWs usuariEntitatAPI,
      String titol, String remitent, FitxerBean fitxerAFirmar, String[][] nifs)
      throws WsI18NException {

    FluxDeFirmesWs fluxDeFirmesWs = constructFluxDeFirmesWsUsingBlocDeFirmes(usuariEntitatAPI, nifs);
    return constructPeticioDeFirma(titol, remitent, fitxerAFirmar, fluxDeFirmesWs);

  }
  
  

  public static PeticioDeFirmaWs constructPeticioDeFirma(String titol, String remitent,
      FitxerBean fitxerAFirmar, FluxDeFirmesWs fluxDeFirmesWs) {
    PeticioDeFirmaWs peticioDeFirmaWs;
    peticioDeFirmaWs = new PeticioDeFirmaWs();
    
    peticioDeFirmaWs.setTitol(titol);
    peticioDeFirmaWs.setDescripcio(titol);      
    peticioDeFirmaWs.setMotiu(titol);
    
    GregorianCalendar gc = (GregorianCalendar)GregorianCalendar.getInstance();
    gc.add(GregorianCalendar.MONTH, 1);
    gc.setTimeInMillis(new Date().getTime());
    peticioDeFirmaWs.setDataCaducitat(new XMLGregorianCalendarImpl(gc));
    
    
    peticioDeFirmaWs.setIdiomaID("ca");
    
    // Sera substituit per l'usuari aplicacio que fa la cridada
    peticioDeFirmaWs.setUsuariAplicacioID(""); 
    peticioDeFirmaWs.setRemitentNom(remitent);
    peticioDeFirmaWs.setRemitentDescripcio(null);
    peticioDeFirmaWs.setTipusDocumentID(1);
    
    peticioDeFirmaWs.setTipusFirmaID(Constants.TIPUSFIRMA_PADES);
    peticioDeFirmaWs.setAlgorismeDeFirmaID(Constants.APPLET_SIGN_ALGORITHM_SHA1WITHRSA);
    peticioDeFirmaWs.setModeDeFirma(Constants.APPLET_SIGN_MODE_IMPLICIT);

    peticioDeFirmaWs.setPosicioTaulaFirmesID(Constants.TAULADEFIRMES_PRIMERAPAGINA);
    
    peticioDeFirmaWs.setFitxerAFirmar(fitxerAFirmar);
    
    peticioDeFirmaWs.setFluxDeFirmes(fluxDeFirmesWs);
    return peticioDeFirmaWs;
  }
  
  
  public static AnnexBean constructAnnexBeanFromResource(String name, String mime,
      boolean adjuntar, boolean firmar) throws Exception {
    
    AnnexBean annex = new AnnexBean();
    annex.setAdjuntar(adjuntar);
    annex.setFirmar(firmar);
    annex.setFitxer(constructFitxerBeanFromResource(name, mime));
    
    return annex;
    
  }
  
  
  public static AnnexBean constructAnnexBeanFromFile(File name, String mime,
      boolean adjuntar, boolean firmar) throws Exception {
    
    AnnexBean annex = new AnnexBean();
    annex.setAdjuntar(adjuntar);
    annex.setFirmar(firmar);
    annex.setFitxer(constructFitxerBeanFromFile(name, mime));
    
    return annex;
    
  }
  
  
  /**
   *
   * @param name
   * @param mime
   * @return
   * @throws Exception
   */
  public static FitxerBean constructFitxerBeanFromResource(String name, String mime) throws Exception  {
    String filename = name;
    if (name.startsWith("/")) {
      filename = name.substring(1);
    } else {
      name = '/' + name; 
    }
    InputStream is = PeticioDeFirmaUtils.class.getResourceAsStream(name);
    
    return constructFitxerBeanFromInputStream(filename, mime, is);

  }
  
  
  public static FitxerBean constructFitxerBeanFromFile(File file, String mime) throws Exception  {
    
    InputStream is = null;
    try {
      is = new FileInputStream(file);
      return constructFitxerBeanFromInputStream(file.getName(), mime, is);
    } finally {
      if (is != null) {
        try { is.close(); } catch (Exception e) { }
      }
    }

  }


  public static FitxerBean constructFitxerBeanFromInputStream(String name, String mime,
      InputStream is) throws IOException {
    FitxerBean fb = new FitxerBean();
    fb.setDescripcio(null);
    fb.setMime(mime);
    fb.setNom(name);

    byte[] data = toByteArray(is);

    fb.setTamany(data.length);
    fb.setData(data);
    
    return fb;
  }
  
  
  public static byte[] toByteArray(InputStream input) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }
    return output.toByteArray();
  }

  
  public static FluxDeFirmesWs constructFluxDeFirmesWs(
      PortaFIBUsuariEntitatWs usuariEntitatAPI, String[] nifs) throws WsI18NException {
    
    String[][] allnifs = nifsInBlock(nifs);
    return constructFluxDeFirmesWsUsingBlocDeFirmes(usuariEntitatAPI, allnifs);
    
  }



  public static String[][] nifsInBlock(String[] nifs) {
    String[][] allnifs = new String[nifs.length][1];
    for (int i = 0; i < nifs.length; i++) {
      allnifs[i][0] = nifs[i];
    }
    return allnifs;
  }
  
  
 public static FluxDeFirmesWs constructFluxDeFirmesWsUsingBlocDeFirmes(
     PortaFIBUsuariEntitatWs usuariEntitatAPI, String[][] nifs) throws WsI18NException {

   String usuariEntitat;
   
   FluxDeFirmesWs flux = new FluxDeFirmesWs();
   flux.setNom("Flux Autogenerat " + System.nanoTime());
   
   for (int i = 0; i < nifs.length; i++) {
     BlocDeFirmesWs bloc = new BlocDeFirmesWs();
     bloc.setMinimDeFirmes(nifs[i].length);
     bloc.setOrdre(i);
     
     for (int j = 0; j < nifs[i].length; j++) {
       // Cercar usuariEntitat associat al nif
       usuariEntitat = usuariEntitatAPI.getUsuariEntitatIDInMyEntitatByAdministrationID(nifs[i][j]);
       
       FirmaBean firma = new FirmaBean();
       firma.setDestinatariID(usuariEntitat);
       firma.setObligatori(true);
       
       bloc.getFirmes().add(firma);
       
     } 
     
     flux.getBlocsDeFirmes().add(bloc);
   }
        
   return flux;
    
 }
}
