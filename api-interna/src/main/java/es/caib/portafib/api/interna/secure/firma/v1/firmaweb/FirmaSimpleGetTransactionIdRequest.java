package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleCommonInfo;

public class FirmaSimpleGetTransactionIdRequest {

    FirmaSimpleCommonInfo firmaSimpleCommonInfo;

    public FirmaSimpleGetTransactionIdRequest() {

    }

    public FirmaSimpleGetTransactionIdRequest(FirmaSimpleCommonInfo firmaSimpleCommonInfo) {
        this.firmaSimpleCommonInfo = firmaSimpleCommonInfo;
    }

    public FirmaSimpleCommonInfo getFirmaSimpleCommonInfo() {
        return firmaSimpleCommonInfo;
    }

    public void setFirmaSimpleCommonInfo(FirmaSimpleCommonInfo firmaSimpleCommonInfo) {
        this.firmaSimpleCommonInfo = firmaSimpleCommonInfo;
    }

}
