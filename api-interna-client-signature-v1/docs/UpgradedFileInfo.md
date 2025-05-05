

# UpgradedFileInfo

Informació de la firma actualitzada

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**signType** | **String** | Tipus de Firma. Valors possibles:      • “PAdES” (Constant SIGN_TYPE_PADES)      • “XAdES” (Constant SIGN_TYPE_XADES)      • “CAdES” (Constant SIGN_TYPE_CADES)      • “FacturaE” (Constant SIGN_TYPE_FACTURAE)      • “OOXML” (Constant SIGN_TYPE_OOXML)      • “ODF” (Constant SIGN_TYPE_ODF)      • “SMIME” (Constant SIGN_TYPE_SMIME)      • “CAdES-ASiC-S” (Constant SIGN_TYPE_CADES_ASIC_S)      • “XAdES-ASiC-S” (Constant SIGN_TYPE_XADES_ASIC_S)      • “PKCS#1” (Constant SIGN_TYPE_PKCS1) |  |
|**signAlgorithm** | **String** | Algorisme de Firma. Valors:      • \&quot;SHA-1\&quot;      • \&quot;SHA-256\&quot;      • \&quot;SHA-384\&quot;      • \&quot;SHA-512\&quot; |  |
|**signMode** | **Integer** | Mode de firma attached (0) o detached (1) |  |
|**eniTipoFirma** | **String** |  |  [optional] |
|**eniPerfilFirma** | **String** |  |  [optional] |
|**validationInfo** | [**ValidationInfo**](ValidationInfo.md) |  |  [optional] |
|**additionInformation** | [**List&lt;KeyValue&gt;**](KeyValue.md) |  |  [optional] |



