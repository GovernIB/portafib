

# SignatureRequestWithFlowTemplateCode

Estructura de dades per a la sol·licitud de signatura electrònica a partir d'un identificador de Plantilla de Flux de Firmes

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**profileCode** | **String** | Perfil a utilitzar per la Firma.Consultar amb administrador del PortaFirmes |  |
|**fileToSign** | [**Document**](Document.md) |  |  |
|**originalDetachedSignature** | [**Document**](Document.md) |  |  [optional] |
|**title** | **String** | Títol de la Petició de Firma |  |
|**description** | **String** | Descripció de la Petició de Firma |  |
|**reason** | **String** | Raó de la realització de la firma |  |
|**documentType** | **Long** | Identificador de Tipus de Document.Els valors base s poden obtenir de l&#39;enumeració DocumentaryTypeConstants però es recomana fer una consulta al mètode getDocumentaryTypes() |  |
|**documentTypeDescription** | **String** | Descripció detallada del Tipus documental. |  [optional] |
|**languageDoc** | **String** | Idioma en que està escrit el document.Valors són &#39;es&#39; o &#39;ca&#39; però es realitzar una cridada al mètode getLanguages() |  |
|**languageUI** | **String** | Idioma de la interficie d&#39;usuari (es o ca) |  |
|**priority** | **Integer** | Prioritat de la Petició. Veure enumeració PriorityConstants. |  |
|**senderName** | **String** | Nom de la persona/aplicació que envia la petició. |  |
|**expirationDate** | **OffsetDateTime** | Data en que caducarà aquesta petició de firma. Si la data es inferior a 3 dies a partir de la data d&#39;avui, llavors es posa 1 mes a partir d&#39;avui. Si val null automàticament es posa a 30 dies a partir de la creació de la petició. |  [optional] |
|**requiresTimeStampInSignature** | **Boolean** | Indica si es requereix que la firma tingui un segell de temps associat. També depen de la politica de Segellat de Temps de l&#39;usuari aplicació. Només es farà cas d&#39;aquest camp si les politiques són POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI o POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO. En el cas de que la politica sigui diferent a les dues anteriors i aquest camp es defineixi, llavors es llançarà un error. |  [optional] |
|**senderDescription** | **String** | Descripció de la persona o responsable de l&#39;aplicació que envia la petició. Es sol posar el correu electronic de la persona que que envia la petició. |  [optional] |
|**expedientCode** | **String** | Codi de l&#39;expedient |  [optional] |
|**expedientName** | **String** | Nom de l&#39;expedient |  [optional] |
|**expedientUrl** | **String** | URL de l&#39;expedient |  [optional] |
|**procedureCode** | **String** | Codi del Procediment  |  [optional] |
|**procedureName** | **String** | Nom del Procediment |  [optional] |
|**additionalInformation** | **String** | Informació Addicional |  [optional] |
|**additionalInformationEvaluable** | **Double** | Informació Addicional avauluable. Per exemple en documents de tipus factura en aquest camp s&#39;insereix la quantitat final de la factura. |  [optional] |
|**annexs** | [**List&lt;Annex&gt;**](Annex.md) | Llista de document annexes a la petició de firma |  [optional] |
|**metadadaList** | [**List&lt;Metadata&gt;**](Metadata.md) | Llista de Metadades associades a la Petició de Firma |  [optional] |
|**flowTemplateCode** | **Long** | Codi de la Plantilla del Flux de Firmes a utilitzar que es troba en el PortaFirmes. PortaFIB actualment no suporta codis en Plantilles de flux de firmes en format String, per la qual cosa aquí s&#39;ha de posar l&#39;ID de BBDD de la Plantilla de Flux de Firmes. |  |



