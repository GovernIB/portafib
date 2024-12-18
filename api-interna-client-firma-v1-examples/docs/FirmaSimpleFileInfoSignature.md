

# FirmaSimpleFileInfoSignature

Informació especifica per a realitzar la firma

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**fileToSign** | [**FirmaSimpleFile**](FirmaSimpleFile.md) |  |  |
|**previusSignatureDetachedFile** | [**FirmaSimpleFile**](FirmaSimpleFile.md) |  |  [optional] |
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
|**additionalInformation** | [**List&lt;FirmaSimpleKeyValue&gt;**](FirmaSimpleKeyValue.md) | Informació Addicional. |  [optional] |



