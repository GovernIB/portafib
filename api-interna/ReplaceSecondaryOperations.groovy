
def dirPath = properties['openapifolder']

def openapiFile = new File("${dirPath}/openapi_signature_v1.json")

if (!openapiFile.exists()) {
    println "⚠️  Archivo no encontrado: ${openapiFile.absolutePath}"
    return
}

def originalContent = openapiFile.getText("UTF-8")

// Reemplaza "operationId": "xxxxx_1" por "operationId": "xxxxx"
def modifiedContent = originalContent.replaceAll(/"operationId"\s*:\s*"([^"]+)_1"/, '"operationId": "$1"')

openapiFile.write(modifiedContent, "UTF-8")

println "✅ operationId secundarios reemplazados en: ${openapiFile.absolutePath}"
