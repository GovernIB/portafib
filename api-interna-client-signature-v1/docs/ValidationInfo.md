

# ValidationInfo

Informació de les validacions realitzades despres de la firma.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**checkAdministrationIDOfSigner** | **Boolean** | S’ha verificat que l’identificador del firmant és la del que estava previst que firmàs. Valor buit indica que no s&#39;ha realitzat la validació. |  [optional] |
|**checkDocumentModifications** | **Boolean** | S’ha verificat que no s’hagi modificat el document original. Valor buit indica que no s&#39;ha realitzat la validació. |  [optional] |
|**checkValidationSignature** | **Boolean** | S’ha verificat que la firma és correcte. Valor buit indica que no s&#39;ha realitzat la validació. |  [optional] |
|**noCheckValidationReason** | **String** | Només s&#39;omple si checkValidationSignature val false |  [optional] |



