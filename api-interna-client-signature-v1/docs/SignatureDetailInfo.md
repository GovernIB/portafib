

# SignatureDetailInfo

Información detallada de la firma

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**algorithm** | **String** |  |  [optional] |
|**digestValue** | **String** |  |  [optional] |
|**signDate** | **OffsetDateTime** |  |  [optional] |
|**validChecks** | [**List&lt;SignatureCheck&gt;**](SignatureCheck.md) |  |  [optional] |
|**invalidChecks** | [**List&lt;SignatureCheck&gt;**](SignatureCheck.md) |  |  [optional] |
|**indeterminateChecks** | [**List&lt;SignatureCheck&gt;**](SignatureCheck.md) |  |  [optional] |
|**policyIdentifier** | **String** |  |  [optional] |
|**certificateInfo** | [**CertificateInformation**](CertificateInformation.md) |  |  [optional] |
|**certificateChain** | **List&lt;byte[]&gt;** |  |  [optional] |
|**timeStampInfo** | [**TimeStampInfo**](TimeStampInfo.md) |  |  [optional] |



