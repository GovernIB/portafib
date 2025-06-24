

# CertificateInformation


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**certificateDescription** | **String** |  |  [optional] |
|**subject** | **String** |  |  [optional] |
|**firstName** | **String** |  |  [optional] |
|**firstSurname** | **String** |  |  [optional] |
|**secondSurname** | **String** |  |  [optional] |
|**surnames** | **String** |  |  [optional] |
|**fullName** | **String** |  |  [optional] |
|**administrationID** | **String** |  |  [optional] |
|**email** | **String** |  |  [optional] |
|**birthDate** | **String** |  |  [optional] |
|**pseudonym** | **String** |  |  [optional] |
|**documentRepresentacio** | **String** | En certificados de Representación, indica el documento que acredita la representación del titular del certificado |  [optional] |
|**cargo** | **String** | Campo obsoleto, se recomienda usar positionInTheCompany |  [optional] |
|**positionInTheCompany** | **String** |  |  [optional] |
|**domainName** | **String** |  |  [optional] |
|**systemOrComponentDescription** | **String** |  |  [optional] |
|**europeanAdministrationID** | **String** |  |  [optional] |
|**europeanOrganizationAdministrationID** | **String** |  |  [optional] |
|**functionaryID** | **String** |  |  [optional] |
|**entityName** | **String** |  |  [optional] |
|**entityAdministrationID** | **String** |  |  [optional] |
|**certificateTypeMinetur** | **Integer** | El mapeo es realizado por el Ministerio de Hacienda y Administraciones  Públicas e incluye a todos los prestadores de certificación reconocidos.Este campo se devolverá para los certificados españoles, para facilitar el tratamiento a aquellas aplicaciones que necesiten admitir tanto a certificados españoles como europeos, ya que las siguientes clasificaciones son equivalentes:  * ESEAL &#x3D;&gt; Clasificación &#x3D; 8.  * ESIG  &#x3D;&gt; Clasificación &#x3D; 0, 5, 7, 11, 12.  * WSA &#x3D;&gt; Clasificación &#x3D; 9.  * UNKNOWN &#x3D;&gt; Clasificación &#x3D; 2, 10. Consultar l&#39;enumeració es.caib.portafib.apiinterna.client.signature.v1.model.CertificateTypeMineturConstants per a més detalls. |  [optional] |
|**certificateTypeEidas** | **String** | Consultar l&#39;enumeració es.caib.portafib.apiinterna.client.signature.v1.model.CertificateTypeEidasConstants per a més detalls.  |  [optional] |
|**certificateQualified** | **Boolean** |  |  [optional] |
|**createdWithASecureDevice** | **Boolean** |  |  [optional] |
|**issuerID** | **String** |  |  [optional] |
|**issuerOrganization** | **String** |  |  [optional] |
|**companyName** | **String** |  |  [optional] |
|**serialNumber** | **String** | serialNumber del certificat |  [optional] |
|**keyUsageCertificate** | **String** |  |  [optional] |
|**keyUsageCertificateExtension** | **String** |  |  [optional] |
|**validSince** | **OffsetDateTime** |  |  [optional] |
|**validUntil** | **OffsetDateTime** |  |  [optional] |
|**policy** | **String** |  |  [optional] |
|**policyVersion** | **String** |  |  [optional] |
|**policyID** | **String** |  |  [optional] |
|**country** | **String** |  |  [optional] |
|**organization** | **String** |  |  [optional] |
|**organizationUnitName** | **String** |  |  [optional] |
|**organizationUnitID** | **String** |  |  [optional] |
|**qcCompliance** | **String** |  |  [optional] |
|**qcSSCD** | **String** |  |  [optional] |
|**idlogOn** | **String** |  |  [optional] |
|**altresValors** | **Map&lt;String, String&gt;** |  |  [optional] |



