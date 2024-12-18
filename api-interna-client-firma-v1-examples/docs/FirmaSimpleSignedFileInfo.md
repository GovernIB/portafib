

# FirmaSimpleSignedFileInfo

Informació del fitxer signat.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**signOperation** | **Integer** | Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2). |  |
|**signType** | **String** | Tipus de Firma. Valors possibles:      - “PAdES” (Constant SIGN_TYPE_PADES)      - “XAdES” (Constant SIGN_TYPE_XADES)      - “CAdES” (Constant SIGN_TYPE_CADES)      - “FacturaE” (Constant SIGN_TYPE_FACTURAE)      - “OOXML” (Constant SIGN_TYPE_OOXML)      - “ODF” (Constant SIGN_TYPE_ODF)      - “SMIME” (Constant SIGN_TYPE_SMIME)      - “CAdES-ASiC-S” (Constant SIGN_TYPE_CADES_ASIC_S)      - “XAdES-ASiC-S” (Constant SIGN_TYPE_XADES_ASIC_S)      - “PKCS#1” (Constant SIGN_TYPE_PKCS1) |  |
|**signAlgorithm** | **String** | Algorisme de Firma. Valors:       - \&quot;SHA-1\&quot;      - \&quot;SHA-256\&quot;      - \&quot;SHA-384\&quot;      - \&quot;SHA-512\&quot; |  |
|**signMode** | **Integer** | Valors:      - 0: Implicit o Attached. La firma resultante incluye internamente una copia de los datos firmados.       - 1: Explicit o Detached: La firma resultante no incluye los datos firmados.  |  |
|**signaturesTableLocation** | **Integer** | Posició de la Taula de firmes:      - 0: Sense taula de firmes      - 1: Taula de firmes en la 1a pàgina      - -1: Darrera pàgina |  |
|**timeStampIncluded** | **Boolean** | Indica si s&#39;ha afegit un segell de Temps durant la firma |  |
|**policyIncluded** | **Boolean** | Indica si inclou política de firma (true, EPES) o no (false) |  |
|**eniTipoFirma** | **String** | Denominación normalizada del tipo de firma. Los posibles valores asignables son los siguientes:       - TF01 - CSV       - TF02 - XAdES internally detached signature\&quot;);       - TF03 - XAdES enveloped signature.      - TF04 - CAdES detached/explicit signature.      - TF05 – CadES attached/implicit signature.      - TF06 - PAdES. El tipo TF04 será establecido por defecto para documentos firmados, exceptuando los documentos en formato PDF o PDF/A, cuyo tipo será TF06. |  [optional] |
|**eniPerfilFirma** | **String** | Perfil empleado en una firma con certificado electrónico. Los posibles valores asignables son los siguientes:    AdES-BES    AdES-EPES    AdES-T    AdES-C    AdES-X    AdES-X1    AdES-X2    AdES-XL    AdES-XL1    AdES-XL2    AdES-A    PAdES-LTV    PAdES-Basic |  [optional] |
|**signerInfo** | [**FirmaSimpleSignerInfo**](FirmaSimpleSignerInfo.md) |  |  |
|**custodyInfo** | [**FirmaSimpleCustodyInfo**](FirmaSimpleCustodyInfo.md) |  |  [optional] |
|**validationInfo** | [**FirmaSimpleValidationInfo**](FirmaSimpleValidationInfo.md) |  |  [optional] |



