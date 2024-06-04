package es.caib.portafib.back.utils;

import es.caib.portafib.persistence.EntitatJPA;
import org.fundaciobit.pluginsib.signature.api.CommonInfoSignature;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signatureweb.api.SignaturesSetWeb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anadal
 *
 */
public class PortaFIBSignaturesSet extends SignaturesSetWeb {

    protected Map<String, List<Long>> pluginsFirmaBySignatureID = null;

    protected Map<String, String> applicationBySignatureID = new HashMap<String, String>();

    protected List<Long> filterByPluginID = null;

    protected final String urlFinalOriginal;

    protected final EntitatJPA entitat;

    protected Long selectedPluginID = null;

    protected boolean redirectToParentWindow;

    protected final int[] originalNumberOfSignsArray;

    protected final String urlBase;

    /**
     * @param signaturesSetID
     * @param expiryDate
     * @param commonInfoSignature
     * @param fileInfoSignatureArray
     */
    public PortaFIBSignaturesSet(String signaturesSetID, Date expiryDate, CommonInfoSignature commonInfoSignature,
            FileInfoSignature[] fileInfoSignatureArray, int[] originalNumberOfSignsArray, EntitatJPA entitat,
            String urlFinal, boolean redirectToParentWindow, String urlBase) {
        super(signaturesSetID, expiryDate, commonInfoSignature, fileInfoSignatureArray, urlFinal);
        this.urlFinalOriginal = this.getUrlFinal();
        this.entitat = entitat;
        this.redirectToParentWindow = redirectToParentWindow;
        this.originalNumberOfSignsArray = originalNumberOfSignsArray;
        this.urlBase = urlBase;
    }

    public Map<String, List<Long>> getPluginsFirmaBySignatureID() {
        return pluginsFirmaBySignatureID;
    }

    public void setPluginsFirmaBySignatureID(Map<String, List<Long>> pluginsFirmaBySignatureID) {
        this.pluginsFirmaBySignatureID = pluginsFirmaBySignatureID;
    }

    public Map<String, String> getApplicationBySignatureID() {
        return applicationBySignatureID;
    }

    public void setApplicationBySignatureID(Map<String, String> applicationBySignatureID) {
        this.applicationBySignatureID = applicationBySignatureID;
    }

    public String getUrlFinalOriginal() {
        return urlFinalOriginal;
    }

    public Long getSelectedPluginID() {
        return selectedPluginID;
    }

    public void setSelectedPluginID(Long selectedPluginID) {
        this.selectedPluginID = selectedPluginID;
    }

    public EntitatJPA getEntitat() {
        return entitat;
    }

    public List<Long> getFilterByPluginID() {
        return filterByPluginID;
    }

    public void setFilterByPluginID(List<Long> filterByPluginID) {
        this.filterByPluginID = filterByPluginID;
    }

    public boolean isRedirectToParentWindow() {
        return redirectToParentWindow;
    }

    public void setRedirectToParentWindow(boolean redirectToParentWindow) {
        this.redirectToParentWindow = redirectToParentWindow;
    }

    public int[] getOriginalNumberOfSignsArray() {
        return originalNumberOfSignsArray;
    }

    public String getUrlBase() {
        return urlBase;
    }

}
