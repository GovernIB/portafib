package es.caib.portafib.back.form;

/**
 * Created 4/09/13 14:30
 *
 * @author mgonzalez
 * @author anadal
 */
public class SeleccioNifForm {
  
   public static final String PARAM_1 = "param1";
   public static final String PARAM_2 = "param2";

   String nif;                                // Nif amb el que fer feina
   String titol;                              // Titol del formulari
   String subtitol;                           // Subtitol del formulari
   String contextWeb;                         // Contexte del formulari
   String cancelUrl;                          //  url de cancel·lació
   
   String param1;  // Parametre opcional 1
   
   String param2;  // Parametre opcional 1

   public SeleccioNifForm() {
     super();
   }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getSubtitol() {
        return subtitol;
    }

    public void setSubtitol(String subtitol) {
        this.subtitol = subtitol;
    }

    public String getContextWeb() {
        return contextWeb;
    }

    public void setContextWeb(String contextWeb) {
        this.contextWeb = contextWeb;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getParam1() {
      return param1;
    }

    public void setParam1(String param1) {
      this.param1 = param1;
    }

    public String getParam2() {
      return param2;
    }

    public void setParam2(String param2) {
      this.param2 = param2;
    }
    
    
}
