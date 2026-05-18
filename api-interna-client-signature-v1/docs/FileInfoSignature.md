

# FileInfoSignature

Informació especifica per a realitzar la firma

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**fileToSign** | [**Document**](Document.md) |  |  |
|**previusSignatureDetachedFile** | [**Document**](Document.md) |  |  [optional] |
|**signID** | **String** | Identificador de la Firma |  |
|**name** | **String** | Nom descriptiu de la firma. Pot ser el nom del fitxer o un nom associat a la tasca per a la que es requereix la firma. |  |
|**reason** | **String** | Raó de la realització de la firma. |  |
|**location** | **String** | Lloc on es realitza la firma. |  |
|**signNumber** | **Integer** | Posició de la firma dins el flux de firma. |  |
|**languageSign** | **String** | Idioma del document. |  |
|**expedientCodi** | **String** | Codi de l&#39;expedient. |  [optional] |
|**expedientNom** | **String** | Nom de l&#39;expedient. |  [optional] |
|**expedientUrl** | **String** | URL de l&#39;expedient. |  [optional] |
|**procedimentCodi** | **String** | Codi del Procediment. |  [optional] |
|**procedimentNom** | **String** | Nom del Procediment. |  [optional] |
|**documentType** | **Long** | Tipus Documental. Si val null se li assigna 99 |  [optional] |
|**requiresTimeStampInSignature** | **Boolean** | Indica si es requereix que la firma tingui un segell de temps associat. També depen de la politica de Segellat de Temps de l&#39;usuari aplicació. Només es farà cas d&#39;aquest camp si les politiques són POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI o POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO. En el cas de que la politica sigui diferent a les dues anterior i aquest valor es defineixi, llavors es llançarà un error. |  [optional] |
|**additionalInformation** | [**List&lt;KeyValue&gt;**](KeyValue.md) | Informació Addicional. |  [optional] |



