/**
 *  @author anadal
 *  @date 20/02/2025
 */

import java.nio.file.*
import groovy.json.*

def processExampleLine(pattern, line) {
    def matcher = line =~ pattern
    if (matcher.find()) {
    /*
        log.info(" =======  GROUP[1] => " + matcher.group(1))
        log.info(" =======  SPLIT => " + matcher.group(1).split('\\|'))
        def values = matcher.group(1).split('\\|').collect { "\"$it\"" }.join(', ')
        log.info(" =======  VALUES => " + values)
        return "        \"x-enum-varnames\" : [ " + values + " ],"
        */
        
        def parts = matcher.group(1).split("\\|")

        // Inicializar listas para los resultados
        def firstList = []
        def secondList = []

        // Procesar cada parte
        parts.each { part ->
            if (part.contains("(") && part.endsWith(")")) {
                // Extraer el primer componente (antes del paréntesis)
                def firstComponent = part.substring(0, part.indexOf("("))
                firstList << "\"$firstComponent\""
                
                // Extraer el contenido dentro de los paréntesis
                def secondComponent = part.substring(part.indexOf("(") + 1, part.length() - 1)
                secondList << "\"$secondComponent\""
            } else {
                // Si no tiene paréntesis, solo añadir a la primera lista entre comillas
                firstList << "\"$part\""
                secondList << "\" \""
            }
        }

        // Convertir las listas a cadenas separadas por comas
        def firstString = firstList.join(", ")
        def secondString = secondList.join(", ")
                
        return [firstString, secondString]
    }
    return [null,null]
}

log.info('\n\n   -------------- PROCESSANT ENUMs EN OPENAPI FILES  -----------------:\n\n')

def dirPath = properties['openapifolder']
log.info(" Open Api Folder: " + dirPath)

def dir = new File(dirPath)

if (dir.exists() && dir.isDirectory()) {
    dir.eachFileMatch(~/.*\.json/) { file ->
        if (file.name != "swaggerui_urls.json") {

            def inputFile = file  // Nombre del archivo de entrada
            def outputFile = file //'output.txt' // Nombre del archivo de salida
            
            def lines = Files.readAllLines(Paths.get(file.absolutePath))
            def updatedLines = []
            
            def pattern = /"example" *: *"([^"]+)"/  // Patrón para capturar el valor de "example"

            lines.eachWithIndex { line, index ->
                //log.info(line); 
                if (line.trim().startsWith('"example"') && index + 1 < lines.size() && lines[index + 1].trim().startsWith('"enum"')) {
                    def (newLine1, newLine2) = processExampleLine(pattern, line)
                    if (newLine1 && newLine2) {
                        updatedLines << "        \"x-enum-varnames\" : [ " + newLine1 + " ],"
                        updatedLines << "        \"x-enum-descriptions\" : [ " + newLine2 + " ],"
                    }
                    
                    if (lines[index - 1].trim().startsWith("\"format\" : \"int32\",")) {
                        
                        for (i in 2..6) {
                           if (updatedLines[updatedLines.size() - i].trim().startsWith("\"type\" : \"string\",") ) {
                              updatedLines[updatedLines.size() - i] = "        \"type\" : \"integer\",";
                              break
                           }
                        }

                    }
                    
                } else {
                    updatedLines << line
                }
            }
            
            Files.write(Paths.get(file.absolutePath), updatedLines)
            log.info("Arxiu " + file.name + " processat correctament.\n")

        }
    }
} else {
    log.error("El directorio " + dirPath + "no existe o no es válido.")
}