package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidacioCompletaRequest {

    protected String entitatID;

    protected boolean validarFitxerFirma;

    protected boolean checkCanviatDocFirmat;

    protected boolean comprovarNifFirma;

    protected IPortaFIBDataSource originalData;

    protected IPortaFIBDataSource signatureData;

    protected IPortaFIBDataSource adaptedData;

    protected IPortaFIBDataSource documentDetachedData;

    protected int signTypeID;

    protected int signMode;

    protected String languageUI;

    protected int numFirmaPortaFIB;

    protected int numFirmesOriginals;

    protected String nifPersonaEsperat;

    protected String nifEmpresaEsperat;

    protected int posTaulaDeFirmes;


    public ValidacioCompletaRequest(String entitatID, boolean validarFitxerFirma, boolean checkCanviatDocFirmat,
            boolean comprovarNifFirma, IPortaFIBDataSource originalData, IPortaFIBDataSource adaptedData,
            IPortaFIBDataSource signatureData, IPortaFIBDataSource documentDetachedData, int signTypeID, int signMode,
            String languageUI, int numFirmaPortaFIB, int numFirmesOriginals, String nifPersonaEsperat,
            String nifEmpresaEsperat, int posTaulaDeFirmes) {
        this.entitatID = entitatID;
        this.validarFitxerFirma = validarFitxerFirma;
        this.checkCanviatDocFirmat = checkCanviatDocFirmat;
        this.comprovarNifFirma = comprovarNifFirma;
        this.originalData = originalData;
        this.adaptedData = adaptedData;
        this.signatureData = signatureData;
        this.documentDetachedData = documentDetachedData;
        this.signTypeID = signTypeID;
        this.signMode = signMode;
        this.languageUI = languageUI;
        this.numFirmaPortaFIB = numFirmaPortaFIB;
        this.numFirmesOriginals = numFirmesOriginals;
        this.nifPersonaEsperat = nifPersonaEsperat;
        this.nifEmpresaEsperat = nifEmpresaEsperat;
        this.posTaulaDeFirmes = posTaulaDeFirmes;
    }

    public int getNumFirmesOriginals() {
        return numFirmesOriginals;
    }

    public void setNumFirmesOriginals(int numFirmesOriginals) {
        this.numFirmesOriginals = numFirmesOriginals;
    }

    public boolean isValidarFitxerFirma() {
        return validarFitxerFirma;
    }

    public void setValidarFitxerFirma(boolean validarFitxerFirma) {
        this.validarFitxerFirma = validarFitxerFirma;
    }

    public int getSignTypeID() {
        return signTypeID;
    }

    public void setSignTypeID(int signTypeID) {
        this.signTypeID = signTypeID;
    }

    public String getEntitatID() {
        return entitatID;
    }

    public void setEntitatID(String entitatID) {
        this.entitatID = entitatID;
    }

    public String getLanguageUI() {
        return languageUI;
    }

    public void setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
    }

    public boolean isCheckCanviatDocFirmat() {
        return checkCanviatDocFirmat;
    }

    public void setCheckCanviatDocFirmat(boolean checkCanviatDocFirmat) {
        this.checkCanviatDocFirmat = checkCanviatDocFirmat;
    }

    public IPortaFIBDataSource getOriginalData() {
        return originalData;
    }

    public void setOriginalData(IPortaFIBDataSource originalData) {
        this.originalData = originalData;
    }

    public IPortaFIBDataSource getSignatureData() {
        return signatureData;
    }

    public void setSignatureData(IPortaFIBDataSource signatureData) {
        this.signatureData = signatureData;
    }

    public IPortaFIBDataSource getDocumentDetachedData() {
        return documentDetachedData;
    }

    public void setDocumentDetachedData(IPortaFIBDataSource documentDetachedData) {
        this.documentDetachedData = documentDetachedData;
    }

    public int getNumFirmaPortaFIB() {
        return numFirmaPortaFIB;
    }

    public void setNumFirmaPortaFIB(int numFirmaPortaFIB) {
        this.numFirmaPortaFIB = numFirmaPortaFIB;
    }

    public boolean isComprovarNifFirma() {
        return comprovarNifFirma;
    }

    public void setComprovarNifFirma(boolean comprovarNifFirma) {
        this.comprovarNifFirma = comprovarNifFirma;
    }

    public String getNifPersonaEsperat() {
        return nifPersonaEsperat;
    }

    public void setNifPersonaEsperat(String nifPersonaEsperat) {
        this.nifPersonaEsperat = nifPersonaEsperat;
    }

    public String getNifEmpresaEsperat() {
        return nifEmpresaEsperat;
    }

    public void setNifEmpresaEsperat(String nifEmpresaEsperat) {
        this.nifEmpresaEsperat = nifEmpresaEsperat;
    }

    public int getSignMode() {
        return signMode;
    }

    public void setSignMode(int signMode) {
        this.signMode = signMode;
    }

    public IPortaFIBDataSource getAdaptedData() {
        return adaptedData;
    }

    public void setAdaptedData(IPortaFIBDataSource adaptedData) {
        this.adaptedData = adaptedData;
    }

    public int getPosTaulaDeFirmes() {
        return posTaulaDeFirmes;
    }

    public void setPosTaulaDeFirmes(int posTaulaDeFirmes) {
        this.posTaulaDeFirmes = posTaulaDeFirmes;
    }

}
