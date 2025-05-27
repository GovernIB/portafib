

# SignatureRequestState

Informació de l'estat d'una Petició de Firma. Valors:      • SignatureRequestStateConstants.NOTSTARTET.getValue()=0      • SignatureRequestStateConstants.RUNNING.getValue()=1      • SignatureRequestStateConstants.PAUSED.getValue()=2      • SignatureRequestStateConstants.REJECTED.getValue()=3      • SignatureRequestStateConstants.SIGNED.getValue()=4

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**state** | **Integer** | Estat de la Peticio de firma. Veure classe SignatureRequestStateConstants. |  |
|**rejectedReason** | **String** | Si l&#39;estat de la Petició de Firma és rebutjat llavors inclou raó de rebuig de la petició. |  [optional] |



