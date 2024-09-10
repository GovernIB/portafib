package es.caib.portafib.callback.beans.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author anadal
 * 
 */

public class SigningRequest {

    @JsonProperty("ID")
    private long iD;

    private String title;

    private int state;

    private String rejectionReason;

    private String additionalInformation;

    private String custodyURL;

    public String getCustodyURL() {
        return custodyURL;
    }

    public void setCustodyURL(String custodyURL) {
        this.custodyURL = custodyURL;
    }

    public long getID() {
        return iD;
    }

    public void setID(long iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}
