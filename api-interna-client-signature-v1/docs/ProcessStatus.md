

# ProcessStatus

Estat d'algun procés

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | **Integer** | Codi de l&#39;estat del procés. StatusConstants.STATUS_FINAL_OK si tot ha anat bé, qualsevol altre valor indica un error o cancel·lació. Pels valors d&#39;aquest camp veure classe StatusConstants |  |
|**errorCode** | **String** | En cas d&#39;error, codi d&#39;error encara que fins i tot en el cas d&#39;error normalment valdrà null. Els valors d&#39;aquest camp són enviats pels diferents plugins de firma, per tant poden variar molt en funció del plugin que s&#39;hagi utilitzat. Per a més informació sobre els valors d&#39;aquest camp, consultar la documentació del plugin de firma que s&#39;hagi utilitzat. |  [optional] |
|**errorMessage** | **String** | En cas d&#39;error, missatge d&#39;error |  [optional] |
|**errorStackTrace** | **String** | En cas d&#39;error, stack trace de l&#39;error en format String |  [optional] |



