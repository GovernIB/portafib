

# FirmaSimpleSignedFileInfo

Informació del fitxer signat.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**SIGN_MODE_ATTACHED_ENVELOPED** | **Integer** | El fitxer de dades resultant inclou la firma: PDF, ODT, ... |  [readonly] |
|**SIGN_MODE_ATTACHED_ENVELOPING** | **Integer** | El fitxer resultant serà la firma que incloura les dades originals |  [readonly] |
|**SIGN_MODE_DETACHED** | **Integer** | El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original |  [readonly] |
|**SIGN_MODE_INTERNALLY_DETACHED** | **Integer** | Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l&#39;XML: ni la firma inclou les dades ni les dades inclouen la firma |  [readonly] |
|**SIGNATURESTABLELOCATION_WITHOUT** | **Integer** | Localitzador de la signatura al document. (Sense signatura visible) |  [readonly] |
|**SIGNATURESTABLELOCATION_FIRSTPAGE** | **Integer** | Localitzador de la signatura al document. (Primera pagina) |  [readonly] |
|**SIGNATURESTABLELOCATION_LASTPAGE** | **Integer** | Localitzador de la signatura al document. (Darrera pagina) |  [readonly] |
|**SIGN_OPERATION_SIGN** | **Integer** | Identificador d&#39;operació per Firma |  [readonly] |
|**SIGN_OPERATION_COSIGN** | **Integer** | Identificador d&#39;operació per Cofirma |  [readonly] |
|**SIGN_OPERATION_COUNTERSIGN** | **Integer** | Identificador d&#39;operació per Contrafirma |  [readonly] |
|**SIGNPROFILE_BES** | **String** | Perfil de firma AdES-BES |  [readonly] |
|**SIGNPROFILE_EPES** | **String** | Perfil de firma AdES-EPES |  [readonly] |
|**SIGNPROFILE_T** | **String** | Perfil de firma AdES-T |  [readonly] |
|**SIGNPROFILE_C** | **String** | Perfil de firma AdES-C |  [readonly] |
|**SIGNPROFILE_X** | **String** | Perfil de firma AdES-X |  [readonly] |
|**SIGNPROFILE_X1** | **String** | Perfil de firma AdES-X1 |  [readonly] |
|**SIGNPROFILE_X2** | **String** | Perfil de firma AdES-X2 |  [readonly] |
|**SIGNPROFILE_XL** | **String** | Perfil de firma AdES-XL |  [readonly] |
|**SIGNPROFILE_XL1** | **String** | Perfil de firma AdES-XL1 |  [readonly] |
|**SIGNPROFILE_XL2** | **String** | Perfil de firma AdES-XL2 |  [readonly] |
|**SIGNPROFILE_A** | **String** | Perfil de firma AdES-A |  [readonly] |
|**SIGNPROFILE_PADES_LTV** | **String** | Perfil de firma PAdES-LTV |  [readonly] |
|**SIGNPROFILE_PADES_BASIC** | **String** | Perfil de firma PAdES-Basic |  [readonly] |
|**signOperation** | **Integer** | Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2). |  |
|**signType** | **String** | Tipus de Firma. Valors possibles:       |  |
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



