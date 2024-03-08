package es.caib.portafib.api.interna.all.exemplereutilitzacio;

import java.util.List;

import org.fundaciobit.pluginsib.utils.rest.ReuseDataPagination;


public class LlistaDeExempleInfoPaginada extends ReuseDataPagination<ExempleInfo> {

    public LlistaDeExempleInfoPaginada() {
        super();
    }

    public LlistaDeExempleInfoPaginada(List<ExempleInfo> data, int page, int pagesize, int totalpages, int totalcount,
            String nextUrl, String dateDownload, String name) {
        super(data, page, pagesize, totalpages, totalcount, nextUrl, dateDownload, name);
    }

}
