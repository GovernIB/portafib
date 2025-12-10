package es.caib.portafib.api.interna.secure.comanda.v1;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import es.caib.comanda.ms.estadistica.model.DiaSetmanaEnum;
import es.caib.comanda.ms.estadistica.model.Dimensio;
import es.caib.comanda.ms.estadistica.model.DimensioDesc;
import es.caib.comanda.ms.estadistica.model.EstadistiquesInfo;
import es.caib.comanda.ms.estadistica.model.Fet;
import es.caib.comanda.ms.estadistica.model.EstadistiquesInfo.EstadistiquesInfoBuilder;
import es.caib.comanda.ms.estadistica.model.Format;
import es.caib.comanda.ms.estadistica.model.IndicadorDesc;
import es.caib.comanda.ms.estadistica.model.RegistreEstadistic;
import es.caib.comanda.ms.estadistica.model.RegistresEstadistics;
import es.caib.comanda.ms.estadistica.model.RegistresEstadistics.RegistresEstadisticsBuilder;
import es.caib.comanda.ms.estadistica.model.Temps;

/**
 * Implementació de l'API d'Estadístiques que COMANDA espera que implementin les APPs.
 * @author anadal
 * 9 dic 2025 15:00:56
 */
public class EstadistiquesService implements EstadistiquesApiInterface {

    @Override
    public EstadistiquesInfo statsInfo() throws IOException {

        EstadistiquesInfoBuilder builder = EstadistiquesInfo.builder();

        List<DimensioDesc> dimensions = List.of(
                DimensioDesc.builder().codi("FIR_SERV").nom("Firmes en Servidor")
                        .valors(List.of("1", "2", "2", "3", "5", "6")).build(),
                DimensioDesc.builder().codi("FIR_WEB_SYNC").nom("Firmes Web Sincrones")
                        .valors(List.of("1", "2", "2", "3", "5", "6")).build(),
                DimensioDesc.builder().codi("FIR_WEB_ASYNC").nom("Firmes Web Asincrones")
                        .valors(List.of("1", "2", "2", "3", "5", "6")).build());
        List<IndicadorDesc> indicadors = List.of(
                IndicadorDesc.builder().codi("TOTAL_FIR").nom("Total de Firmes").format(Format.LONG).build(),
                IndicadorDesc.builder().codi("FIR_EXITO").nom("Firmes Exitoses").format(Format.LONG).build(),
                IndicadorDesc.builder().codi("FIR_ERROR").nom("Firmes Errònies").format(Format.LONG).build());

        return builder.codi("PFI").data(new Date()).dimensions(dimensions).indicadors(indicadors).build();

    }

    @Override
    public RegistresEstadistics estadistiques(HttpServletRequest request) throws IOException {

        RegistresEstadisticsBuilder builder = RegistresEstadistics.builder();

        List<Dimensio> dimensions = null;

        List<Fet> fets = null;

        List<RegistreEstadistic> regEstadistic = List
                .of(RegistreEstadistic.builder().dimensions(dimensions).fets(fets).build()

                );

        Temps temps = Temps.builder().anualitat(1).data(new Date()).dia(25).diaSetmana(DiaSetmanaEnum.DL).mes(1)
                .setmana(25).trimestre(1).build();

        return builder.fets(regEstadistic).temps(temps).build();
    }

    @Override
    public RegistresEstadistics estadistiques(HttpServletRequest request, String data) throws Exception {
        // 
        return estadistiques(request);
    }

    @Override
    public List<RegistresEstadistics> estadistiques(HttpServletRequest request, String dataInici, String dataFi)
            throws Exception {
        return List.of(estadistiques(request));
    }

}
