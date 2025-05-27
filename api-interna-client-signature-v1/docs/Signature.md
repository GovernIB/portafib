

# Signature

Definició d'una Firma

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**signer** | [**Signer**](Signer.md) |  |  |
|**required** | **Boolean** | És obligatori que aquesta persona firmi |  |
|**reason** | **String** | Raó de firma específica per aquesta firma. Sinó es defineix s&#39;utilitzarà la raó definida en la Petició de Firma. |  [optional] |
|**minimumNumberOfRevisers** | **Integer** | Número mínim de revisors. Per defecte 0. |  |
|**revisers** | [**List&lt;Reviser&gt;**](Reviser.md) | Llistat de revisors de la Firma. Abans de que aquest destinatari firma, els revisors hauran d&#39;haver acceptat el document. |  [optional] |



