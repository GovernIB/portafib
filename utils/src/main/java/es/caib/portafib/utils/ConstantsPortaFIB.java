package es.caib.portafib.utils;

/**
 * 
 * @author anadal
 *
 */
public interface ConstantsPortaFIB {

  //==============================================================
  // --- US DE POLITICA DE FIRMA - PORTAFIB v2.0 #148 -----
  // =============================================================

  // -1=> usar politica de firma de l'entitat,
  public static final int US_POLITICA_DE_FIRMA_DEFINIT_EN_ENTITAT = -1;
  
  // 0 => no usar politica de firma, 
  public static final int US_POLITICA_DE_FIRMA_NO_USAR = 0;

  // 1=> usar politica d'aquesta configuracio 
  public static final int US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT = 1;
  
  // 2 => L'usuari web o usuari-app elegeixen la politica de firma
  public static final int US_POLITICA_DE_FIRMA_OPCIONAL = 2;
  
  

  public static final int[] US_POLITICA_DE_FIRMA_EN_ENTITAT = {
    US_POLITICA_DE_FIRMA_NO_USAR,
    US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT,
    US_POLITICA_DE_FIRMA_OPCIONAL
  };
  
  
  public static final int[] US_POLITICA_DE_FIRMA_EN_USR_APP_CONFIG = {
    US_POLITICA_DE_FIRMA_DEFINIT_EN_ENTITAT,
    US_POLITICA_DE_FIRMA_NO_USAR,
    US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT,
    US_POLITICA_DE_FIRMA_OPCIONAL
  };
  
  
  
  //==============================================================
  // --- POLITICA DE TAULA DE FIRMES - PORTAFIB v2.0 #166 -----
  // =============================================================
  
  // [USR_APP_CONFIG] -1 definit en l'entitat,
 public static final int POLITICA_TAULA_FIRMES_DEFINIT_EN_ENTITAT = -1;
  
  
  // 0 no es permet taules de firmes
  public static final int POLITICA_TAULA_FIRMES_NO_ES_PERMET = 0;
  
  // 1  obligatori politica definida en la configuració d'usuari aplicació o entitat
  public static final int POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT = 1;
  

  // 2 opcional, per defecte el definit a l'entitat o conf. de usuari aplicacio
  public static final int POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF = 2;
  



  public static final int[] POLITICA_TAULA_FIRMES_EN_ENTITAT = {
    POLITICA_TAULA_FIRMES_NO_ES_PERMET,
    POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT,
    POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF 
  };
  
  public static final int[] POLITICA_TAULA_FIRMES_EN_USR_APP_CONFIG = {
    POLITICA_TAULA_FIRMES_DEFINIT_EN_ENTITAT,
    POLITICA_TAULA_FIRMES_NO_ES_PERMET,
    POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT,
    POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF 
  };
  
  
  
  //========================================================
  // ----- POLITICA DE CUSTODIA - PORTAFIB v2.0 #165 ------
  // =======================================================
  
  public static enum POLITICA_CUSTODIA {
    POLITICA_CUSTODIA_ENTITAT,
    POLITICA_CUSTODIA_USUARI_APLICACIO, 
    POLITICA_CUSTODIA_USUARI_ENTITAT;
  }
  
  // TOTES les de l'entitat
  public static final int[] POLITICA_CUSTODIA_ENTITAT = new int[] {
    ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE,
    //ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT,
    //  ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT, => Es igual que la següent
    ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO,
    ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU,
    ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU,
    ConstantsV2. POLITICA_CUSTODIA_LLIBERTAT_TOTAL
  };
  
  //TOTES les de la configuració d'usuari aplicació
  public static final int[] POLITICA_CUSTODIA_USUARI_APLICACIO = new int[] { 
    ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT,
    ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE,
    //ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT,
    ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO,
    ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU,
    ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU,
    ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL
  };
  
  
  //TOTES les de la configuració d'usuari entitat
  public static final int[] POLITICA_CUSTODIA_USUARI_ENTITAT = new int[] { 
    ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT,
    ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE,
    //ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT,
    ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO,
    ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_ACTIU,
    ConstantsV2.POLITICA_CUSTODIA_SENSE_CUSTODIA_O_POLITICA_DEFINIDA_EN_ENTITAT_PER_DEFECTE_NO_ACTIU,
    ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL
  };
  

  // ========================================================
  // ----- POLITICA DE SEGELLAT DE TEMPS ------
  // ========================================================


  public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT=-1;
  
  public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR=0;
  public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI=1;
  public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI=2;
  public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO=3;
  
  
  // ========================================================
  // ----- POLITICA DE SEGELLAT DE TEMPS ------
  // ========================================================
  
  //TOTES les de l'entitat
 public static final int[] POLITICA_DE_SEGELLAT_DE_TEMPS_EN_ENTITAT = new int[] {
   POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR,
   POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI,
   POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI,
   POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO
 };
 
 //TOTES les de la configuració d'usuari aplicació
 public static final int[] POLITICA_DE_SEGELLAT_DE_TEMPS_EN_USR_APP_CONFIG = new int[] {
   POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT,
   POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR,
   POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI,
   POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI,
   POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO
 };
  
  
  
}
