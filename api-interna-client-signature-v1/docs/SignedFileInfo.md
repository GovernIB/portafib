

# SignedFileInfo

Informació del fitxer signat.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**signOperation** | **Integer** | Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2). |  |
|**signType** | **String** | Tipus de Firma. Valors possibles veure SignTypeConstants. |  |
|**signAlgorithm** | **String** | Algorisme de Firma. Valors:       - \&quot;SHA-1\&quot;      - \&quot;SHA-256\&quot;      - \&quot;SHA-384\&quot;      - \&quot;SHA-512\&quot; |  |
|**signMode** | **Integer** | Mode de firma. Valors veure SignModeConstants. Exemple SignModeConstants.SIGN_MODE_ATTACHED_ENVELOPED.value() |  |
|**signaturesTableLocation** | **Integer** | Posició de la Taula de firmes:Veure classe SignaturesTableLocationConstants |  |
|**timeStampIncluded** | **Boolean** | Indica si s&#39;ha afegit un segell de Temps durant la firma |  |
|**policyIncluded** | **Boolean** | Indica si inclou política de firma (true, EPES) o no (false) |  |
|**eniTipoFirma** | **String** | Denominación normalizada del tipo de firma. Los posibles valores asignables son los siguientes:       - TF01 - CSV       - TF02 - XAdES internally detached signature\&quot;);       - TF03 - XAdES enveloped signature.      - TF04 - CAdES detached/explicit signature.      - TF05 – CadES attached/implicit signature.      - TF06 - PAdES. El tipo TF04 será establecido por defecto para documentos firmados, exceptuando los documentos en formato PDF o PDF/A, cuyo tipo será TF06. |  [optional] |
|**eniPerfilFirma** | **String** | Perfil empleado en una firma con certificado electrónico. Los posibles valores asignables son los siguientes:    AdES-BES    AdES-EPES    AdES-T    AdES-C    AdES-X    AdES-X1    AdES-X2    AdES-XL    AdES-XL1    AdES-XL2    AdES-A    PAdES-LTV    PAdES-Basic |  [optional] |
|**signers** | [**List&lt;SignerInfo&gt;**](SignerInfo.md) | Informació del signant o signants |  [optional] |
|**custodyInfo** | [**CustodyInfo**](CustodyInfo.md) |  |  [optional] |
|**validationInfo** | [**ValidationInfo**](ValidationInfo.md) |  |  [optional] |



