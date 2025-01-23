

# RestExceptionInfo

Estructura de dades utilitzada per passar informació d'un error

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**errorCode** | **Integer** | Codi intern de l&#39;error. Si l&#39;Aplicació no gestiona codis d&#39;error llavors val null. |  [optional] |
|**errorMessage** | **String** | Missatge de l&#39;error |  |
|**stackTrace** | **String** | Stacktrace de l&#39;excepció |  [optional] |
|**stackTraceCause** | **String** | Stacktrace de l&#39;excepció causant de l&#39;error si n&#39;hi hagués. |  [optional] |
|**field** | **String** | Indica el camp en que hi ha un error de validació. |  [optional] |



