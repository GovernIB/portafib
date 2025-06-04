
def dirPath = properties['openapifolder']

def openapiFile = new File("${dirPath}/openapi_signature_v1.json")

if (!openapiFile.exists()) {
    println "⚠️  Archivo no encontrado: ${openapiFile.absolutePath}"
    return
}

def originalContent = openapiFile.getText("UTF-8")

// Reemplaza "operationId": "xxxxx_1" por "operationId": "xxxxx"
def modifiedContent1 = originalContent.replaceAll(/"operationId"\s*:\s*"([^"]+)_1"/, '"operationId": "$1"')

def modifiedContent2 = modifiedContent1.replaceAll(/"operationId"\s*:\s*"([^"]+)_2"/, '"operationId": "$1"')

def modifiedContent3 = modifiedContent2.replaceAll(/"operationId"\s*:\s*"([^"]+)_3"/, '"operationId": "$1"')

def modifiedContent4 = modifiedContent3.replaceAll(/"operationId"\s*:\s*"([^"]+)_4"/, '"operationId": "$1"')

openapiFile.write(modifiedContent4, "UTF-8")

println "✅ operationId secundarios reemplazados en: ${openapiFile.absolutePath}"
