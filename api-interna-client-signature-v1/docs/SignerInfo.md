

# SignerInfo

Informació del signant

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**eniRolFirma** | **String** | Esquemas desarrollados a nivel local y que pueden incluir valores como válida, autentica, refrenda, visa, representa, testimonia, etc.. |  [optional] |
|**eniSignerName** | **String** | Nombre o razón social de los firmantes. |  [optional] |
|**eniSignerAdministrationId** | **String** | NIF del firmant. |  [optional] |
|**eniSignLevel** | **String** | Indicador normalizado que refleja el grado de  confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano, Firma electrónica avanzada, Claves concertadas, Firma electrónica avanzada basada en certificados, CSV, .. |  [optional] |
|**signDate** | **OffsetDateTime** | Data en que es va realitzar la firma |  [optional] |
|**serialNumberCert** | **String** | Número de Sèrie del Certificat utilitzat en la firma |  [optional] |
|**issuerCert** | **String** | Issuer del Certificat utilitzat en la firma |  [optional] |
|**subjectCert** | **String** | Subject del Certificat utilitzat en la firma |  [optional] |
|**signPlugin** | [**SignPlugin**](SignPlugin.md) |  |  [optional] |
|**additionalInformation** | [**List&lt;KeyValue&gt;**](KeyValue.md) | Ofrecer cualquier otra información que se considere útil acerca del firmante. |  [optional] |



