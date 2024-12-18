

# HttpHeaders


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**empty** | **Boolean** |  |  [optional] |
|**location** | **URI** |  |  [optional] |
|**lastModified** | **Long** |  |  [optional] |
|**connection** | **List&lt;String&gt;** |  |  [optional] |
|**contentLength** | **Long** |  |  [optional] |
|**contentType** | [**MediaType**](MediaType.md) |  |  [optional] |
|**date** | **Long** |  |  [optional] |
|**ifModifiedSince** | **Long** |  |  [optional] |
|**accessControlExposeHeaders** | **List&lt;String&gt;** |  |  [optional] |
|**accessControlRequestHeaders** | **List&lt;String&gt;** |  |  [optional] |
|**accessControlRequestMethod** | [**AccessControlRequestMethodEnum**](#AccessControlRequestMethodEnum) |  |  [optional] |
|**accessControlAllowMethods** | [**List&lt;AccessControlAllowMethodsEnum&gt;**](#List&lt;AccessControlAllowMethodsEnum&gt;) |  |  [optional] |
|**accessControlMaxAge** | **Long** |  |  [optional] |
|**accessControlAllowCredentials** | **Boolean** |  |  [optional] |
|**ifUnmodifiedSince** | **Long** |  |  [optional] |
|**accessControlAllowHeaders** | **List&lt;String&gt;** |  |  [optional] |
|**accessControlAllowOrigin** | **String** |  |  [optional] |
|**range** | **List&lt;Object&gt;** |  |  [optional] |
|**origin** | **String** |  |  [optional] |
|**etag** | **String** |  |  [optional] |
|**expires** | **Long** |  |  [optional] |
|**allow** | [**Set&lt;AllowEnum&gt;**](#Set&lt;AllowEnum&gt;) |  |  [optional] |
|**ifMatch** | **List&lt;String&gt;** |  |  [optional] |
|**ifNoneMatch** | **List&lt;String&gt;** |  |  [optional] |
|**vary** | **List&lt;String&gt;** |  |  [optional] |
|**cacheControl** | **String** |  |  [optional] |
|**acceptCharset** | [**List&lt;HttpHeadersAcceptCharsetInner&gt;**](HttpHeadersAcceptCharsetInner.md) |  |  [optional] |
|**upgrade** | **String** |  |  [optional] |
|**pragma** | **String** |  |  [optional] |
|**accept** | [**List&lt;MediaType&gt;**](MediaType.md) |  |  [optional] |
|**all** | **Map&lt;String, String&gt;** |  |  [optional] |



## Enum: AccessControlRequestMethodEnum

| Name | Value |
|---- | -----|
| GET | &quot;GET&quot; |
| HEAD | &quot;HEAD&quot; |
| POST | &quot;POST&quot; |
| PUT | &quot;PUT&quot; |
| PATCH | &quot;PATCH&quot; |
| DELETE | &quot;DELETE&quot; |
| OPTIONS | &quot;OPTIONS&quot; |
| TRACE | &quot;TRACE&quot; |



## Enum: List&lt;AccessControlAllowMethodsEnum&gt;

| Name | Value |
|---- | -----|
| GET | &quot;GET&quot; |
| HEAD | &quot;HEAD&quot; |
| POST | &quot;POST&quot; |
| PUT | &quot;PUT&quot; |
| PATCH | &quot;PATCH&quot; |
| DELETE | &quot;DELETE&quot; |
| OPTIONS | &quot;OPTIONS&quot; |
| TRACE | &quot;TRACE&quot; |



## Enum: Set&lt;AllowEnum&gt;

| Name | Value |
|---- | -----|
| GET | &quot;GET&quot; |
| HEAD | &quot;HEAD&quot; |
| POST | &quot;POST&quot; |
| PUT | &quot;PUT&quot; |
| PATCH | &quot;PATCH&quot; |
| DELETE | &quot;DELETE&quot; |
| OPTIONS | &quot;OPTIONS&quot; |
| TRACE | &quot;TRACE&quot; |



