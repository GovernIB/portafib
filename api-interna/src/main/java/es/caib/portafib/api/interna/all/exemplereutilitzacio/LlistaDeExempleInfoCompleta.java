package es.caib.portafib.api.interna.all.exemplereutilitzacio;

import java.util.List;

import org.fundaciobit.pluginsib.utils.rest.ReuseDataAllElements;

/**
 * 
 * @author anadal
 *
 */
public class LlistaDeExempleInfoCompleta extends ReuseDataAllElements<ExempleInfo> {

    public LlistaDeExempleInfoCompleta() {
        super();
    }

    public LlistaDeExempleInfoCompleta(List<ExempleInfo> data, int totalcount, String dateDownload, String name) {
        super(data, totalcount, dateDownload, name);
    }
    

}
