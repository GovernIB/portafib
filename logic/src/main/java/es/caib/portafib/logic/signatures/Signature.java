package es.caib.portafib.logic.signatures;

import java.util.Date;

public class Signature {

    private final String signerName;
    private final String signerAdministrationId;
    private final String organizationName;
    private final String organizationAdministrationId;
    private final Date signingTime;

    protected static class Builder {

        private Date signingTime;
        private String signerName;
        private String signerAdministrationId;
        private String organizationName;
        private String organizationAdministrationId;

        public Builder signingTime(Date signingTime) {
            this.signingTime = signingTime;
            return this;
        }

        public Builder signerName(String signerName) {
            this.signerName = signerName;
            return this;
        }

        public Builder signerAdministrationId(String signerAdministrationId) {
            this.signerAdministrationId = signerAdministrationId;
            return this;
        }

        public Builder organizationName(String organizationName) {
            this.organizationName = organizationName;
            return this;
        }

        public Builder organizationAdministrationId(String organizationAdministrationId) {
            this.organizationAdministrationId = organizationAdministrationId;
            return this;
        }

        public Signature build() {
            return new Signature(this);
        }
    }

    private Signature(Builder builder) {
        this.signingTime = builder.signingTime;
        this.signerName = builder.signerName;
        this.signerAdministrationId = builder.signerAdministrationId;
        this.organizationAdministrationId = builder.organizationAdministrationId;
        this.organizationName = builder.organizationName;
    }

    public String getSignerName() {
        return signerName;
    }

    public String getSignerAdministrationId() {
        return signerAdministrationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOrganizationAdministrationId() {
        return organizationAdministrationId;
    }

    public Date getSigningTime() {
        return signingTime;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "signerName='" + signerName + '\'' +
                ", signerAdministrationId='" + signerAdministrationId + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", organizationAdministrationId='" + organizationAdministrationId + '\'' +
                ", signingTime=" + signingTime +
                '}';
    }

}
