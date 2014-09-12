package es.caib.portafib.back.form;

import es.caib.portafib.model.entity.UsuariPersona;
import org.fundaciobit.genapp.common.StringKeyValue;

import java.util.List;

/**
 * Created 2/07/13 12:00
 *
 * @author mgonzalez
 */
public class SeleccioCarrecForm {

   String nif;
   UsuariPersona up;                          // UsuariPersona que volem crear o modificar.
   String usuariPersona;                      // Es guarda l'usuaripersonaid del combo de usuaripersona en cas de càrrec.
   List<StringKeyValue> listOfUsuariPersona;   // LListat d'usuaris persona de la entitat que podem associar a un usuarientitat càrrec
   String carrec;                             // Càrrec de l'usuari entitat
   String idCarrec;                           // Identificar del càrrec
   String entitatID;                          // Identificador de la entitat.
   String titol;                              // Titol del formulari
   String subtitol;                           // Subtitol del formulari
   String contextWeb;                         // Contexte del formulari

   public SeleccioCarrecForm() {
     super();
   }


    public UsuariPersona getUp() {
        return up;
    }

    public void setUp(UsuariPersona up) {
        this.up = up;
    }

    public String getUsuariPersona() {
        return usuariPersona;
    }

    public void setUsuariPersona(String usuariPersona) {
        this.usuariPersona = usuariPersona;
    }

    public List<StringKeyValue> getListOfUsuariPersona() {
        return listOfUsuariPersona;
    }

    public void setListOfUsuariPersona(List<StringKeyValue> listOfUsuariPersona) {
        this.listOfUsuariPersona = listOfUsuariPersona;
    }

    public String getCarrec() {
        return carrec;
    }

    public void setCarrec(String carrec) {
        this.carrec = carrec;
    }

    public String getIdCarrec() {
        return idCarrec;
    }

    public void setIdCarrec(String idCarrec) {
        this.idCarrec = idCarrec;
    }

    public String getEntitatID() {
        return entitatID;
    }

    public void setEntitatID(String entitatID) {
        this.entitatID = entitatID;
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
}
