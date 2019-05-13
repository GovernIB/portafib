package es.caib.portafib.back.controller.aden;

import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;

/**
 *
 * explicacio conté l'explicació de perquè el fitxer és així com és (si és buid perquè és buid, si no és buid, q conté)
 * ordreBloc serà null si és un fitxer que no prové d'una firma, o contendrà l'ordre del bloc de firma si hi prové
 */
public class FitxerPeticioFirma extends FitxerBean {

    private String destinatari;
    private String origen;

    public String getDestinatari() {
        return destinatari;
    }

    public void setDestinatari(String destinatari) {
        this.destinatari = destinatari;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public FitxerPeticioFirma(Fitxer __bean, String origen, String descripcio, String destinatari) {
        super(__bean);
        this.origen = origen;
        this.setDescripcio(this.getDescripcio() == null ? descripcio : this.getDescripcio().concat("\n " + descripcio));
        this.destinatari = destinatari;
    }

    public FitxerPeticioFirma(long fitxerID, String origen, String descripcio, String destinatari) {
        setFitxerID(fitxerID);
        this.origen = origen;
        this.setDescripcio(this.getDescripcio() == null ? descripcio : this.getDescripcio().concat("\n " + descripcio));
        this.destinatari = destinatari;
    }
}
