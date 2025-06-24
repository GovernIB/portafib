package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Informació sobre un certificat
 * 
 * @author anadal
 */
public class CertificateInformation {

    /**
     * certificateType 
     */
    private String certificateDescription;

    private String subject;

    /**
     * nomResponsable
     */
    private String firstName;
    /**
     * primerLlinatgeResponsable
     * 
     */
    private String firstSurname;
    /**
     * segonLlinatgeResponsable
     * 
     */
    private String secondSurname;
    /**
     * llinatgesResponsable
     */
    private String surnames;
    /**
     * nomCompletResponsable
     */
    private String fullName;

    /**
     * nifResponsable: DNI del responsable)
     */
    @Schema(description = "DNI del responsable del certificat")
    private String administrationID;

    @Schema(description = "Email del responsable del certificat")
    private String email;

    // dataNaixement
    @Schema(description = "Data de naixement del responsable del certificat")
    private String birthDate;

    @Schema(
            description = "Pseudónimo del titular del certificat, si aplica. "
                    + "En certificats de tipus 7 (Empleat Públic amb pseudònim) és obligatori")
    private String pseudonym;

    @Schema(
            description = "En certificats de Representació,"
                    + " indica el document que acredita la representació del titular del certificat")
    private String representationDocument;

    /**
     * carrec o Cargo
     * 
     * NOTA: NO trobo diferencia amb positionInTheCompany (puesto o lloc de feina) 
     */
    @Schema(deprecated = true, description = "Camp obsolet, es recomana usar positionInTheCompany")
    @Deprecated
    private String cargo;

    /**
     * puesto (puesto desempeñado por el suscriptor del certificado dentro de la administración)
     * 
     * llocDeFeina
     */
    private String positionInTheCompany;

    /**
     * * 9 – Cualificado de autenticación de sitio web (UE 910/2014)
    o Relativos al subject:
     NombreDominioIP (dominio del sitio web, tal y somo figura en el Subject Alternative Names)
    3 – Sede (no cualificado)
    o Relativos al subject:
     NombreDominioIP (dominio al que pertenece la sede)
     */
    @Schema(description= "Domini del lloc web, tal y como figura en el Subject Alternative Names")
    private String domainName;

    /** En certificados de Sello: DenominaciónSistemaComponente (breve descripción del sistema o componente)
     entidadSuscriptora 
    (denominacioSistemaComponent)
    */
    private String systemOrComponentDescription;

    /**
     * ID_ europeo (DNI o identificador europeo, con codificación estándar según ETSI EN 319 412 )
     * 
     * idEuropeu
     */
    @Schema(description= "DNI o identificador europeu del responsable del certificat, amb codificació estàndard segons ETSI EN 319 412")
    private String europeanAdministrationID;

    /** OI_Europeo (NIF o identificador de la organización europeo, con codificación estándar según ETSI EN 319 412 ) 
     * 
     * oiEuropeu
     * 
     * */

    @Schema(description = "NIF o identificador de l'organització europeu del responsable del certificat, amb codificació estàndard segons ETSI EN 319 412")
    private String europeanOrganizationAdministrationID;

    /** Solo en certificadoes de tipo 5: Empleado Público (se corresponde con el NRP o NIP) 
     * 
     * 
     * Los funcionarios públicos, al ingresar en la administración, reciben dos identificadores importantes que permiten su identificación y el seguimiento de su trayectoria administrativa: el NRP y el NIP.
    
    NRP (Número de Registro de Personal): Es un código numérico único asignado a cada funcionario cuando es inscrito en el Registro Central de Personal. El NRP permite identificar de forma exclusiva a cada funcionario dentro de la administración. Es como tu «DNI» dentro de la Administración General del Estado.
    NIP (Número de Identificación de Personal): Es otro código asignado por la administración, aunque se utiliza en diferentes contextos, como la identificación en sistemas electrónicos o para el acceso a determinados servicios. El NIP es utilizado para la gestión interna de los empleados públicos y puede variar dependiendo del organismo en el que trabajes.
     * 
     * 
     * numeroIdentificacionPersonal
     * */
    private String functionaryID; // 

    // (entidad propietaria del certificado)
    // entitatSubscriptoraNom
    private String entityName;

    // entitatSubscriptoraNif
    private String entityAdministrationID;

    @Schema(
            description = "El mapeo es realizado por el Ministerio de Hacienda y Administraciones\r\n"
                    + "Públicas e incluye a todos los prestadores de certificación reconocidos."
                    + "Este campo se devolverá para los certificados españoles, para facilitar el"
                    + " tratamiento a aquellas aplicaciones que necesiten admitir tanto a certificados españoles"
                    + " como europeos, ya que las siguientes clasificaciones son equivalentes:"
                    + "\n * ESEAL => Clasificación = 8." + "\n * ESIG  => Clasificación = 0, 5, 7, 11, 12."
                    + "\n * WSA => Clasificación = 9." + "\n * UNKNOWN => Clasificación = 2, 10.\n"
                    + "Consultar l'enumeració es.caib.portafib.apiinterna.client.signature.v1.model.CertificateTypeMineturConstants per a més detalls.")
    // clasificacion
    private int certificateTypeMinetur = CertificateTypeMineturConstants.CLASSIFICACIO_DESCONEGUDA.value;

    // EIDAS Classification (certClassification)
    /**
     * eidasCl
     */
    @Schema(
            description = "Consultar l'enumeració es.caib.portafib.apiinterna.client.signature.v1.model.CertificateTypeEidasConstants per a més detalls. ")
    private String certificateTypeEidas;

    /**
     * certQualified: + false: El certificado no es cualificado (reconocido). + true: El certificado
     * es cualificado. + null: Se desconoce (Se considera no cualificado a menos que se
     * indique lo contrario en certClasification).
     */
    private Boolean certificateQualified;

    // qualified signature creation device (QSCD).
    private Boolean createdWithASecureDevice;

    @Schema(description = "Nom de qui ha emès el certificat")
    private String issuerID;
    /**
     * emissorOrganitzacio: Nom de l'organització emissora del certificat
     */
    @Schema(description = "Nom de l'organització emissora del certificat")
    private String issuerOrganization;

    // razon social raoSocial
    private String companyName;

    @Schema(description = "SerialNumber del certificat")
    private String serialNumber;

    /**
     * usCertificat
     */
    private String keyUsageCertificate;
    /**
     * 
     * usCertificatExtensio
     */
    private String keyUsageCertificateExtension;

    private Date validSince;
    private Date validUntil;

    /**
     * 
     * According to X.509, a certificate policy is "a named set of
    rules that indicates the applicability of a certificate to a
    particular community and/or class of application with common security
    requirements.
    
     * politica
     */
    private String policy;

    /** 
     * politicaVersio
     */
    private String policyVersion;
    /** 
     * politicaID
     * 
     * Identificador de la política de firma (si aplica)
     */
    private String policyID;

    private String country;

    private String organization;
    /**
     * 
     * unitatOrganitzativa
     */
    private String organizationUnitName;

    /*
     * 
     * unitatOrganitzativaNifCif: NIF o CIF de la unitat organitzativa propietària del certificat
     */
    private String organizationUnitID;

    /** Indica si el certificado es cualificado EU según ETSi 319 412-5 */
    private String qcCompliance;

    /**
     * Indica si la clave privada reside en un dispositivo hardware cualificado según ETSi 319
     * 412-5
     */
    private String qcSSCD;

    /** Identificador de log-on del Sistema Operatiu */
    private String idlogOn;

    private Map<String, String> altresValors = new HashMap<String, String>();

    public CertificateInformation() {
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String nomCompletResponsable) {
        this.fullName = nomCompletResponsable;
    }

    public String getCertificateDescription() {
        return certificateDescription;
    }

    public void setCertificateDescription(String tipusCertificat) {
        this.certificateDescription = tipusCertificat;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSystemOrComponentDescription() {
        return systemOrComponentDescription;
    }

    public void setSystemOrComponentDescription(String denominacioSistemaComponent) {
        this.systemOrComponentDescription = denominacioSistemaComponent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String nomDomini) {
        this.domainName = nomDomini;
    }

    public void setFirstName(String nomResponsable) {
        this.firstName = nomResponsable;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String primerLlinatgeResponsable) {
        this.firstSurname = primerLlinatgeResponsable;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String segonLlinatgeResponsable) {
        this.secondSurname = segonLlinatgeResponsable;
    }

    public String getAdministrationID() {
        return administrationID;
    }

    public void setAdministrationID(String nifResponsable) {
        this.administrationID = nifResponsable;
    }

    public String getRepresentationDocument() {
        return representationDocument;
    }

    public void setRepresentationDocument(String documentRepresentacio) {
        this.representationDocument = documentRepresentacio;
    }

    public String getIssuerID() {
        return issuerID;
    }

    public void setIssuerID(String emissorID) {
        this.issuerID = emissorID;
    }

    public String getOrganizationUnitID() {
        return organizationUnitID;
    }

    public void setOrganizationUnitID(String unitatOrganitzativaNifCif) {
        this.organizationUnitID = unitatOrganitzativaNifCif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String dataNaixement) {
        this.birthDate = dataNaixement;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String raoSocial) {
        this.companyName = raoSocial;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String numeroSerie) {
        this.serialNumber = numeroSerie;
    }

    public String getKeyUsageCertificate() {
        return keyUsageCertificate;
    }

    public void setKeyUsageCertificate(String usCertificat) {
        this.keyUsageCertificate = usCertificat;
    }

    public String getKeyUsageCertificateExtension() {
        return keyUsageCertificateExtension;
    }

    public void setKeyUsageCertificateExtension(String usCertificatExtensio) {
        this.keyUsageCertificateExtension = usCertificatExtensio;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validFins) {
        this.validUntil = validFins;
    }

    public Date getValidSince() {
        return validSince;
    }

    public void setValidSince(Date validDesDe) {
        this.validSince = validDesDe;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String politica) {
        this.policy = politica;
    }

    public String getPolicyVersion() {
        return policyVersion;
    }

    public void setPolicyVersion(String politicaVersio) {
        this.policyVersion = politicaVersio;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String politicaID) {
        this.policyID = politicaID;
    }

    public String getIssuerOrganization() {
        return issuerOrganization;
    }

    public void setIssuerOrganization(String emissorOrganitzacio) {
        this.issuerOrganization = emissorOrganitzacio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String pais) {
        this.country = pais;
    }

    public String getOrganizationUnitName() {
        return organizationUnitName;
    }

    public void setOrganizationUnitName(String unitatOrganitzativa) {
        this.organizationUnitName = unitatOrganitzativa;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String llinatgesResponsable) {
        this.surnames = llinatgesResponsable;
    }

    public String getEuropeanAdministrationID() {
        return europeanAdministrationID;
    }

    public void setEuropeanAdministrationID(String idEuropeu) {
        this.europeanAdministrationID = idEuropeu;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entitatSubscriptoraNom) {
        this.entityName = entitatSubscriptoraNom;
    }

    public String getEntityAdministrationID() {
        return entityAdministrationID;
    }

    public void setEntityAdministrationID(String entitatSubscriptoraNif) {
        this.entityAdministrationID = entitatSubscriptoraNif;
    }

    public int getCertificateTypeMinetur() {
        return certificateTypeMinetur;
    }

    public void setCertificateTypeMinetur(int certificateTypeMinetur) {
        this.certificateTypeMinetur = certificateTypeMinetur;
    }

    public String getCertificateTypeEidas() {
        return certificateTypeEidas;
    }

    public void setCertificateTypeEidas(String certificateTypeEidas) {
        this.certificateTypeEidas = certificateTypeEidas;
    }

    public Boolean getCertificateQualified() {
        return certificateQualified;
    }

    public void setCertificateQualified(Boolean certificatQualificat) {
        this.certificateQualified = certificatQualificat;
    }

    public String getPositionInTheCompany() {
        return positionInTheCompany;
    }

    public void setPositionInTheCompany(String llocDeFeina) {
        this.positionInTheCompany = llocDeFeina;
    }

    public Boolean getCreatedWithASecureDevice() {
        return createdWithASecureDevice;
    }

    public void setCreatedWithASecureDevice(Boolean creatAmbUnDispositiuSegur) {
        this.createdWithASecureDevice = creatAmbUnDispositiuSegur;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organitzacio) {
        this.organization = organitzacio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String carrec) {
        this.cargo = carrec;
    }

    public String getEuropeanOrganizationAdministrationID() {
        return europeanOrganizationAdministrationID;
    }

    public void setEuropeanOrganizationAdministrationID(String oiEuropeu) {
        this.europeanOrganizationAdministrationID = oiEuropeu;
    }

    public Map<String, String> getAltresValors() {
        return altresValors;
    }

    public void setAltresValors(Map<String, String> altresValors) {
        this.altresValors = altresValors;
    }

    public String getQcCompliance() {
        return qcCompliance;
    }

    public void setQcCompliance(String qcCompliance) {
        this.qcCompliance = qcCompliance;
    }

    public String getQcSSCD() {
        return qcSSCD;
    }

    public void setQcSSCD(String qcSSCD) {
        this.qcSSCD = qcSSCD;
    }

    public String getIdlogOn() {
        return idlogOn;
    }

    public void setIdlogOn(String idlogOn) {
        this.idlogOn = idlogOn;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonim) {
        this.pseudonym = pseudonim;
    }

    public String getFunctionaryID() {
        return functionaryID;
    }

    public void setFunctionaryID(String numeroIdentificacionPersonal) {
        this.functionaryID = numeroIdentificacionPersonal;
    }

    /*
    @Override
    public String toString() {
        StringBuffer st = new StringBuffer();
    
        st.append("Tipus Certificat: ").append(getCertificateType()).append("\n");
        st.append("Classificació Espanya (" + getClassification() + "): ");
        switch (getClassification()) {
            case CLASSIFICACIO_NO_QUALIFICATS:
                st.append("COMPONENTS");
            break;
            case CLASSIFICACIO_PERSONA_FISICA:
                st.append("PERSONA FISICA");
            break;
            case CLASSIFICACIO_PERSONA_JURIDICA:
                st.append("PERSONA JURIDICA");
            break;
            case CLASSIFICACIO_SEU_ELECTRONICA:
                st.append("Sede según la ley 40/2015(no cualificado)");
            break;
    
            case CLASSIFICACIO_SEGELL:
                st.append("Sello según la ley 40/2015(no cualificado)");
            break;
    
            case CLASSIFICACIO_EMPLEAT_PUBLIC:
                st.append("Empleado Público según la ley 40/2015");
            break;
    
            case CLASSIFICACIO_ENTITAT_SENSE_PERSONALITAT_JURIDICA:
                st.append("Entidad sin personalidad jurídica (no cualificado)");
            break;
    
            case CLASSIFICACIO_EMPLEAT_PUBLIC_AMB_PSEUDONIM:
                st.append("Empleado público con seudónimo según el RD 1671/2009");
            break;
    
            case CLASSIFICACIO_SEGELL_QUALIFICAT:
                st.append("Cualificado de sello, según el reglamente UE 910/2014");
            break;
    
            case CLASSIFICACIO_AUTENTIFICACIO_QUALIFICAT:
                st.append("Cualificado de autenticación, según el reglamente UE 910/2014");
            break;
    
            case CLASSIFICACIO_SEGELL_DE_TEMPS:
                st.append("Cualificado de servicio cualificado de sello de tiempo");
            break;
    
            case CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_PERSONA_JURIDICA:
                st.append("Persona  física  representante  ante  las  Administraciones  Públicas de persona jurídica");
            break;
    
            case CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_ENTITAT:
                st.append(
                        "Persona  física  representante  ante  las  Administraciones Públicas de entidad sin persona jurídica");
            break;
    
            default:
                st.append("Desconegut");
        }
        st.append(" (").append(getClassification()).append(")").append("\n");
    
        {
            String eidasClassificacio = getClassificacioEidas();
    
            if (eidasClassificacio != null) {
                st.append("Classificacio EIDAS (" + eidasClassificacio + "): ");
                if (EIDAS_ESEAL.equals(eidasClassificacio)) {
    
                    st.append(
                            " Certificado de sello electrónico. Pertenece a una persona jurídica pero NO incluye un custodio. (ESEAL)\n");
                } else if (EIDAS_ESIG.equals(eidasClassificacio)) {
                    st.append("Certificado para firma electrónica (persona física, ESIG).");
                } else if (EIDAS_WSA.equals(eidasClassificacio)) {
                    st.append("Certificado para autenticación de servidor web (WSA)");
                } else if (EIDAS_UNKNOWN.equals(eidasClassificacio)) {
                    st.append(
                            "Se desconoce el tipo del certificado (Se considera no  cualificado a menos que se indique lo contrario en certQualified)(UNKNOWN)");
                } else {
                    st.append(" Classificació desconeguda => " + eidasClassificacio);
                }
    
                st.append("\n");
            }
        }
    
        st.append("Subject: ").append(getSubject()).append("\n");
    
        st.append("Nom Complet Responsable: ").append(getFullName()).append("\n");
    
        st.append("Nom Responsable: ").append(getFirstName()).append("\n");
        st.append("Primer Llinatge Responsable: ").append(getFirstSurname()).append("\n");
        st.append("Segon Llinatge Responsable: ").append(getSecondSurname()).append("\n");
        st.append("Llinatges Responsable: ").append(getSurnames()).append("\n");
        st.append("NIF Responsable: ").append(getAdministrationID()).append("\n");
    
        st.append("Numero Identificacion Personal: ").append(getFunctionaryID()).append("\n");
    
        st.append("ID Europeu: ").append(getEuropeanAdministrationID()).append("\n");
        st.append("OI Europeu: ").append(getEuropeanOrganizationAdministrationID()).append("\n");
    
        st.append("Email: ").append(getEmail()).append("\n");
        st.append("Data Naixement: ").append(getBirthDate()).append("\n");
        st.append("Rao Social: ").append(getCompanyName()).append("\n");
    
        st.append("Document Representació: ").append(getDocumentRepresentacio()).append("\n");
    
        st.append("Pseudonim: ").append(getPseudonym()).append("\n");
    
        st.append("Lloc de Feina: ").append(getPositionInTheCompany()).append("\n");
        st.append("Càrrec: ").append(getCargo()).append("\n");
    
        st.append("Nom Domini: ").append(getDomainName()).append("\n");
        st.append("Denominació Sistema Component: ").append(getSystemOrComponentDescription()).append("\n");
    
        st.append("Entitat Subscriptora Nom: ").append(getEntityName()).append("\n");
        st.append("Entitat Subscriptora Nif/Cif: ").append(getEntityAdministrationID()).append("\n");
    
        st.append("Organització Nom: ").append(getOrganization()).append("\n");
        st.append("Organització Nif/Cif: ").append(getOrganizationUnitID()).append("\n");
    
        st.append("Unitat Organitzativa: ").append(getOrganizationUnitName()).append("\n");
    
        st.append("Pais: ").append(getCountry()).append("\n");
    
        st.append("Emissor ID: ").append(getIssuerID()).append("\n");
        st.append("Emissor Organització: ").append(getIssuerOrganization()).append("\n");
        st.append("Numero Serie: ").append(getSerialNumber()).append("\n");
        st.append("Us Certificat: ").append(getKeyUsageCertificate()).append("\n");
        st.append("Us Certificat Extes: ").append(getKeyUsageCertificateExtension()).append("\n");
    
        {
            Boolean cq = getCertificateQualified();
            st.append("Certificat Qualificat: ").append(cq == null ? "Desconegut" : (cq ? "Si" : "No")).append("\n");
        }
    
        {
            Boolean cq = getCreatedWithASecureDevice();
            st.append("Creat amb un Dispositiu Qualificat de Firma: ")
                    .append(cq == null ? "Desconegut" : (cq ? "Si" : "No")).append("\n");
        }
    
        st.append("Valid des de: ").append(getValidSince()).append("\n");
        st.append("Valid fins: ").append(getValidUntil()).append("\n");
        st.append("Politica: ").append(getPolicy()).append("\n");
        st.append("Politica Versio: ").append(getPolicyVersion()).append("\n");
        st.append("Politica ID: ").append(getPolicyID()).append("\n");
    
        if (getQcCompliance() != null) {
            st.append("QcCompliance: ").append(getQcCompliance()).append("\n");
        }
    
        if (getQcSSCD() != null) {
            st.append("QcSSCD: ").append(getQcSSCD()).append("\n");
        }
    
        if (getIdlogOn() != null) {
            st.append("IDlogOn: ").append(getIdlogOn()).append("\n");
        }
    
        if (altresValors != null && altresValors.size() != 0) {
    
            st.append("\n").append(" -- Altres Valors --").append("\n");
            for (String key : altresValors.keySet()) {
                st.append("- ").append(key).append(" =>").append(altresValors.get(key)).append("\n");
            }
    
        }
    
        return st.toString();
    
    }
    */
    public String[] compareTo(CertificateInformation obj) {

        if (this == obj) {
            return null; // és la mateixa instancia
        }

        if (obj == null) {
            return new String[] { "La Informacio de Certificat Generada val null" };
        }

        ArrayList<String> errors = new ArrayList<String>();

        comparaCamp(errors, cargo, obj.cargo, "Càrrec");

        comparaCamp(errors, certificateQualified, obj.certificateQualified, "CertificatQualificat");

        comparaCamp(errors, certificateTypeMinetur, obj.certificateTypeMinetur, "classificacio");

        comparaCamp(errors, certificateTypeEidas, obj.certificateTypeEidas, "classificacioEidas");

        comparaCamp(errors, createdWithASecureDevice, obj.createdWithASecureDevice, "creatAmbUnDispositiuSegur");

        comparaCamp(errors, birthDate, obj.birthDate, "dataNaixement");
        comparaCamp(errors, systemOrComponentDescription, obj.systemOrComponentDescription,
                "denominacioSistemaComponent");

        comparaCamp(errors, representationDocument, obj.representationDocument, "documentRepresentacio");
        comparaCamp(errors, email, obj.email, "email");

        comparaCamp(errors, issuerID, obj.issuerID, "emissorID");

        comparaCamp(errors, issuerOrganization, obj.issuerOrganization, "emissorOrganitzacio");

        comparaCamp(errors, entityAdministrationID, obj.entityAdministrationID, "entitatSubscriptoraNif");

        comparaCamp(errors, entityName, obj.entityName, "entitatSubscriptoraNom");

        comparaCamp(errors, europeanAdministrationID, obj.europeanAdministrationID, "idEuropeu");

        comparaCamp(errors, surnames, obj.surnames, "llinatgesResponsable");

        comparaCamp(errors, positionInTheCompany, obj.positionInTheCompany, "llocDeFeina");
        comparaCamp(errors, administrationID, obj.administrationID, "nifResponsable");
        comparaCamp(errors, fullName, obj.fullName, "nomCompletResponsable");

        comparaCamp(errors, domainName, obj.domainName, "nomDomini");

        comparaCamp(errors, firstName, obj.firstName, "nomResponsable");

        comparaCamp(errors, functionaryID, obj.functionaryID, "numeroIdentificacionPersonal");

        comparaCamp(errors, serialNumber, obj.serialNumber, "numeroSerie");

        comparaCamp(errors, europeanOrganizationAdministrationID, obj.europeanOrganizationAdministrationID,
                "oiEuropeu");

        comparaCamp(errors, organization, obj.organization, "organitzacio");

        comparaCamp(errors, country, obj.country, "pais");

        comparaCamp(errors, policy, obj.policy, "politica");

        comparaCamp(errors, policyID, obj.policyID, "politicaID");

        comparaCamp(errors, policyVersion, obj.policyVersion, "politicaVersio");

        comparaCamp(errors, firstSurname, obj.firstSurname, "primerLlinatgeResponsable");

        comparaCamp(errors, pseudonym, obj.pseudonym, "pseudonim");

        comparaCamp(errors, companyName, obj.companyName, "raoSocial");

        comparaCamp(errors, secondSurname, obj.secondSurname, "segonLlinatgeResponsable");

        comparaCamp(errors, subject, obj.subject, "subject");

        comparaCamp(errors, certificateDescription, obj.certificateDescription, "tipusCertificat");

        comparaCamp(errors, organizationUnitName, obj.organizationUnitName, "unitatOrganitzativa");

        comparaCamp(errors, organizationUnitID, obj.organizationUnitID, "unitatOrganitzativaNifCif");

        comparaCamp(errors, keyUsageCertificate, obj.keyUsageCertificate, "usCertificat");

        comparaCamp(errors, keyUsageCertificateExtension, obj.keyUsageCertificateExtension, "usCertificatExtensio");

        comparaCamp(errors, validSince, obj.validSince, "validDesDe");

        comparaCamp(errors, validUntil, obj.validUntil, "validFins");

        // comparaCamp(errors, idlogOn, obj.idlogOn, "idlogOn")

        // comparaCamp(errors, qcCompliance,obj.qcCompliance,"qcCompliance");

        // comparaCamp(errors, qcSSCD,obj.qcSSCD,"qcSSCD");

        if (errors.size() == 0) {
            return null;
        } else {
            return errors.toArray(new String[errors.size()]);
        }

    }

    private String comparaCamp(ArrayList<String> errors, Object expected, Object generated, String fieldName) {
        if (expected == null) {
            if (generated != null) {
                errors.add("El valor del camp " + fieldName + " és diferent EG [" + expected + " | " + generated + "]");
            }
        } else if (!expected.equals(generated)) {
            errors.add("El valor del camp " + fieldName + " és diferent EG [" + expected + " | " + generated + "]");
        }
        return null;
    }

    private String comparaCamp(ArrayList<String> errors, String expected, String generated, String fieldName) {
        if (expected == null) {
            if (generated != null) {
                errors.add("El valor del camp " + fieldName + " és diferent EG [" + expected + " | " + generated + "]");
            }
        } else if (!expected.equalsIgnoreCase(generated)) {
            errors.add("El valor del camp " + fieldName + " és diferent EG [" + expected + " | " + generated + "]");
        }
        return null;
    }

}
