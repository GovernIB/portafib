package es.caib.portafib.logic.events;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public class FirmaEvent implements Cloneable, Serializable {
  
  public static final int PRIORITAT_ALTA = 8;
  
  public static final int PRIORITAT_NORMAL = 4;
  
  public static final int PRIORITAT_BAIXA = 1;
  
  public static final String BASE_PATH_REBREAVIS = "REBREAVIS";
  

  protected transient final Logger log = Logger.getLogger(getClass());
  

  private transient static final long serialVersionUID = 12344545645L;

  private long eventID;
  
  private int prioritat;
  
  private Date dateEvent;

  //private EstatDeFirma estatDeFirma;
  

  // És la persona que ha realitzat l'acció (firmat, rebutjat, validat, ...)
  private String actorUsuariEntitatID; 

  // És la persona a qui va dirigida la informació
  private String destinatariUsuariEntitatID;

  private String destinatariUsuariAplicacioID;
  
  
  private String href;


  //==========  EstatDeFirma ============
  
  private String estatDeFirmaUsuariEntitatID;
  
  private Long firmaID;
  
  private Long estatDeFirmaColaboracioDelegacioID;
  
  private String estatDeFirmaColaboracioDelegacioDestinatariID;
  
  private String estatDeFirmaDescripcio;
  
  private Date estatDeFirmaDataFi;
  
  private Long tipusEstatDeFirmaFinal;
  
  
  //  ==========  PeticioDeFirma ============
  private long peticioDeFirmaID;
  
  private String peticioDeFirmaTitol;
  
  private String peticioDeFirmaUsuariAplicacioID;
  
  private String peticioDeFirmaUsuariEntitatID;
  
  private int tipusEstatPeticioDeFirmaID;
  
  private String peticioDeFirmaInfoAdicional;
  
  private boolean signAnnexos;
  
  private String custodyURL;
  
  public FirmaEvent() {
    
  }
  

  /**
   * @param eventID
   * @param estatDeFirma
   * @param peticioDeFirma
   * @throws Exception 
   */
  public FirmaEvent(long eventID, int prioritat, PeticioDeFirmaJPA peticioDeFirma) throws I18NException  {
    this(eventID, prioritat, peticioDeFirma , null);
  }

  /**
   * @param eventID
   * @param estatDeFirma
   * @param peticioDeFirma
   * @throws Exception 
   */
  public FirmaEvent(long eventID, int prioritat, PeticioDeFirmaJPA peticioDeFirma,
      EstatDeFirma estatDeFirma) throws I18NException  {
    super();
    dateEvent= new Date();
    this.eventID = eventID;
    this.prioritat = prioritat;
    // Estat de Firma
    if (estatDeFirma != null) {
      this.estatDeFirmaUsuariEntitatID = estatDeFirma.getUsuariEntitatID();
      this.firmaID = estatDeFirma.getFirmaID();
      if (estatDeFirma.getDataFi() != null) {
        this.estatDeFirmaDataFi = new Date(estatDeFirma.getDataFi().getTime());
        this.tipusEstatDeFirmaFinal = estatDeFirma.getTipusEstatDeFirmaFinalID();
      }
      this.estatDeFirmaColaboracioDelegacioID = estatDeFirma.getColaboracioDelegacioID();
      if (this.estatDeFirmaColaboracioDelegacioID != null) {
        ColaboracioDelegacioLogicaLocal cdEjb;
        
        cdEjb = EjbManager.getColaboracioDelegacioEJB();
        
        ColaboracioDelegacioJPA cd = cdEjb.findByPrimaryKeyFull(this.estatDeFirmaColaboracioDelegacioID);
        this.estatDeFirmaColaboracioDelegacioDestinatariID = cd.getDestinatariID();
        
      }
      this.estatDeFirmaDescripcio = estatDeFirma.getDescripcio();
    } else {
      this.estatDeFirmaDescripcio = peticioDeFirma.getMotiuDeRebuig();
    }
    // Peticio de Firma
    this.peticioDeFirmaID = peticioDeFirma.getPeticioDeFirmaID();
    this.peticioDeFirmaTitol = peticioDeFirma.getTitol();
    // Això és informació que es retorna quan el document es retornat (downloadDocument)
    // Tiquet pendent #130
    this.peticioDeFirmaInfoAdicional = peticioDeFirma.getInformacioAdicional();
    this.peticioDeFirmaUsuariAplicacioID = peticioDeFirma.getUsuariAplicacioID();
    this.peticioDeFirmaUsuariEntitatID = peticioDeFirma.getUsuariEntitatID();
    this.tipusEstatPeticioDeFirmaID = peticioDeFirma.getTipusEstatPeticioDeFirmaID();
    
    if (eventID == Constants.NOTIFICACIOAVIS_PETICIO_FIRMADA) {
      CustodiaInfo custodia = peticioDeFirma.getCustodiaInfo();
      if (custodia != null) {
        this.custodyURL = custodia.getUrlFitxerCustodiat();
      }
    }
    
    this.signAnnexos = signAnnexos(peticioDeFirma);
    
  }

  
  
  

  public String getCustodyURL() {
    return custodyURL;
  }


  public void setCustodyURL(String custodyURL) {
    this.custodyURL = custodyURL;
  }


  /**
   * Calcula si algun annex ha de ser firmat
   * @param peticioDeFirma
   * @return
   */
  private boolean signAnnexos(PeticioDeFirmaJPA peticioDeFirma) {
    try {
      Set<AnnexJPA> annexos = peticioDeFirma.getAnnexs();
      if (annexos == null || annexos.size() == 0) {
        return false;
      }
      
      for (AnnexJPA annexJPA : annexos) {
        if (annexJPA.isFirmar()) {
          return true;
        }
      }
      
      return false;
      
    } catch (Exception e) {
      
      log.error("Error en signAnnexos(): " + e.getMessage(),e );
      
      return false;
    }
  }
  
  
  
  public boolean isSignAnnexos() {
    return signAnnexos;
  }


  public void setSignAnnexos(boolean signAnnexos) {
    this.signAnnexos = signAnnexos;
  }

  
  
/*
  public UsuariEntitatJPA getActorUsuariEntitat() {
    return actorUsuariEntitat;
  }

  public void setActorUsuariEntitat(UsuariEntitatJPA actorUsuariEntitat) {
    this.actorUsuariEntitat = actorUsuariEntitat;
  }

  public UsuariEntitatJPA getDestinatariUsuariEntitat() {
    return destinatariUsuariEntitat;
  }

  public void setDestinatariUsuariEntitat(UsuariEntitatJPA destinatariUsuariEntitat) {
    this.destinatariUsuariEntitat = destinatariUsuariEntitat;
  }
*/

  public Long getTipusEstatDeFirmaFinal() {
    return tipusEstatDeFirmaFinal;
  }


  public void setTipusEstatDeFirmaFinal(Long tipusEstatDeFirmaFinal) {
    this.tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinal;
  }


  public long getEventID() {
    return eventID;
  }

  public void setEventID(long eventID) {
    this.eventID = eventID;
  }
  /*
  public EstatDeFirma getEstatDeFirma() {
    return estatDeFirma;
  }
  
  public void setEstatDeFirma(EstatDeFirma estatDeFirma) {
    this.estatDeFirma = estatDeFirma;
  }
  
/*
  public void setPeticioDeFirma(PeticioDeFirma peticioDeFirma) {
    this.peticioDeFirma = peticioDeFirma;
  }


  public PeticioDeFirma getPeticioDeFirma() {
    return peticioDeFirma;
  }
*/
  
  
  
  public String getHref() {
    return href;
  }

  public String getActorUsuariEntitatID() {
    return actorUsuariEntitatID;
  }


  public void setActorUsuariEntitatID(String actorUsuariEntitatID) {
    this.actorUsuariEntitatID = actorUsuariEntitatID;
  }


  public void setHref(String href) {
    this.href = href;
  }
  
  
  

  public long getPeticioDeFirmaID() {
    return peticioDeFirmaID;
  }


  public void setPeticioDeFirmaID(long peticioDeFirmaID) {
    this.peticioDeFirmaID = peticioDeFirmaID;
  }


  public String getPeticioDeFirmaTitol() {
    return peticioDeFirmaTitol;
  }


  public void setPeticioDeFirmaTitol(String peticioDeFirmaTitol) {
    this.peticioDeFirmaTitol = peticioDeFirmaTitol;
  }


  public String getDestinatariUsuariAplicacioID() {
    return destinatariUsuariAplicacioID;
  }


  public void setDestinatariUsuariAplicacioID(String destinatariUsuariAplicacioID) {
    this.destinatariUsuariAplicacioID = destinatariUsuariAplicacioID;
  }


  public String getPeticioDeFirmaUsuariAplicacioID() {
    return peticioDeFirmaUsuariAplicacioID;
  }


  public void setPeticioDeFirmaUsuariAplicacioID(String peticioDeFirmaUsuariAplicacioID) {
    this.peticioDeFirmaUsuariAplicacioID = peticioDeFirmaUsuariAplicacioID;
  }

  

  public String getPeticioDeFirmaUsuariEntitatID() {
    return peticioDeFirmaUsuariEntitatID;
  }


  public void setPeticioDeFirmaUsuariEntitatID(String peticioDeFirmaUsuariEntitatID) {
    this.peticioDeFirmaUsuariEntitatID = peticioDeFirmaUsuariEntitatID;
  }


  public int getTipusEstatPeticioDeFirmaID() {
    return tipusEstatPeticioDeFirmaID;
  }


  public void setTipusEstatPeticioDeFirmaID(int tipusEstatPeticioDeFirmaID) {
    this.tipusEstatPeticioDeFirmaID = tipusEstatPeticioDeFirmaID;
  }

  
  
  
  public Date getDateEvent() {
    return dateEvent;
  }


  public void setDateEvent(Date dateEvent) {
    this.dateEvent = dateEvent;
  }


  public String getEstatDeFirmaUsuariEntitatID() {
    return estatDeFirmaUsuariEntitatID;
  }


  public void setEstatDeFirmaUsuariEntitatID(String estatDeFirmaUsuariEntitatID) {
    this.estatDeFirmaUsuariEntitatID = estatDeFirmaUsuariEntitatID;
  }
  
  


  public Long getFirmaID() {
    return firmaID;
  }


  public void setFirmaID(Long firmaID) {
    this.firmaID = firmaID;
  }


  public Long getEstatDeFirmaColaboracioDelegacioID() {
    return estatDeFirmaColaboracioDelegacioID;
  }


  public void setEstatDeFirmaColaboracioDelegacioID(Long estatDeFirmaColaboracioDelegacioID) {
    this.estatDeFirmaColaboracioDelegacioID = estatDeFirmaColaboracioDelegacioID;
  }


  public String getEstatDeFirmaDescripcio() {
    return estatDeFirmaDescripcio;
  }


  public void setEstatDeFirmaDescripcio(String estatDeFirmaDescripcio) {
    this.estatDeFirmaDescripcio = estatDeFirmaDescripcio;
  }


  public String getEstatDeFirmaColaboracioDelegacioDestinatariID() {
    return estatDeFirmaColaboracioDelegacioDestinatariID;
  }


  public void setEstatDeFirmaColaboracioDelegacioDestinatariID(
      String estatDeFirmaColaboracioDelegacioDestinatariID) {
    this.estatDeFirmaColaboracioDelegacioDestinatariID = estatDeFirmaColaboracioDelegacioDestinatariID;
  }


  public Date getEstatDeFirmaDataFi() {
    return estatDeFirmaDataFi;
  }


  public void setEstatDeFirmaDataFi(Date estatDeFirmaDataFi) {
    this.estatDeFirmaDataFi = estatDeFirmaDataFi;
  }

  
  

  public String getDestinatariUsuariEntitatID() {
    return destinatariUsuariEntitatID;
  }


  public void setDestinatariUsuariEntitatID(String destinatariUsuariEntitatID) {
    this.destinatariUsuariEntitatID = destinatariUsuariEntitatID;
  }


  public int getPrioritat() {
    return prioritat;
  }


  public void setPrioritat(int prioritat) {
    this.prioritat = prioritat;
  }

  public String getPeticioDeFirmaInfoAdicional() {
    return peticioDeFirmaInfoAdicional;
  }


  public void setPeticioDeFirmaInfoAdicional(String peticioDeFirmaInfoAdicional) {
    this.peticioDeFirmaInfoAdicional = peticioDeFirmaInfoAdicional;
  }


  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
  
  
  

}
