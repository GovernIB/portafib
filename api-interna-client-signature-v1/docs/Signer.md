

# Signer

Classe que representa un firmant. Només s'ha d'omplir un camp dels que conté.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**positionInTheCompany** | **String** | Identificador que representa un Càrrec. Exemples: fundaciobit_gerent, caib_president, ... |  [optional] |
|**administrationID** | **String** | Identificador administratiu. En el cas de PortaFIB serà NIF, NIE, ... |  [optional] |
|**username** | **String** | Nom d&#39;usuari que té la persona en la corporació o entitat. Exemples: u806666 o anadal |  [optional] |
|**intermediateServerUsername** | **String** | ID intern del servidor intermedi (en el nostre cas PortaFIB). Exemples: fundaciobit_anadal, caib_u80067, ... |  [optional] |
|**externalSigner** | [**ExternalSigner**](ExternalSigner.md) |  |  [optional] |



