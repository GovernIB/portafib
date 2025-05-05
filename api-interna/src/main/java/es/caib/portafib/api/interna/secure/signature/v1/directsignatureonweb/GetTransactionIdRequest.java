package es.caib.portafib.api.interna.secure.signature.v1.directsignatureonweb;

import es.caib.portafib.api.interna.secure.signature.v1.commons.CommonInfo;

public class GetTransactionIdRequest {

    CommonInfo firmaSimpleCommonInfo;

    public GetTransactionIdRequest() {

    }

    public GetTransactionIdRequest(CommonInfo firmaSimpleCommonInfo) {
        this.firmaSimpleCommonInfo = firmaSimpleCommonInfo;
    }

    public CommonInfo getFirmaSimpleCommonInfo() {
        return firmaSimpleCommonInfo;
    }

    public void setFirmaSimpleCommonInfo(CommonInfo firmaSimpleCommonInfo) {
        this.firmaSimpleCommonInfo = firmaSimpleCommonInfo;
    }

}
