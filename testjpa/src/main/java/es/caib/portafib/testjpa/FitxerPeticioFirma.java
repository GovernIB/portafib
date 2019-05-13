package es.caib.portafib.testjpa;

import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;

/**
 *
 * explicacio conté l'explicació de perquè el fitxer és així com és (si és buid perquè és buid, si no és buid, q conté)
 * ordreBloc serà null si és un fitxer que no prové d'una firma, o contendrà l'ordre del bloc de firma si hi prové
 */
public class FitxerPeticioFirma extends FitxerBean {

    private String explicacio;

    private Integer ordreBloc;

    public String getExplicacio() {
        return explicacio;
    }

    public void setExplicacio(String explicacio) {
        this.explicacio = explicacio;
    }

    public Integer getOrdreBloc() {
        return ordreBloc;
    }

    public void setOrdreBloc(Integer ordreBloc) {
        this.ordreBloc = ordreBloc;
    }

    public FitxerPeticioFirma(Fitxer __bean, String explicacio, Integer ordreBloc) {
        super(__bean);
        this.explicacio = explicacio;
        this.ordreBloc = ordreBloc;
    }

    public FitxerPeticioFirma(String explicacio, Integer ordreBloc) {
        setFitxerID(-1);
        this.explicacio = explicacio;
        this.ordreBloc = ordreBloc;
    }
}
