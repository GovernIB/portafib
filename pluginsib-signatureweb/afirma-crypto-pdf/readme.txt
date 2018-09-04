


Aquest readme descriu les pases per generar una nova versió de la llibreria afirma-crypto-pdf 
per poder suportar estampació de taula de firmes.

(1) Anar a src/main/java/es/gob/afirma/signers/pades/ i renombrar els fitxers:
      * PdfPreProcessor.java   => PdfPreProcessor.java_COPIA
      * PdfSessionManager.java => PdfSessionManager.java_COPIA

(2) Obtenir codi font de la nova versió de afirma-crypto-pdf (Si no es té llavors es pot decompilar emprant Java Decompiler)

(3) En el nostre cas només requerim fer canvis als fitxers PdfPreProcessor i PdfSessionManager.
    Copiar els fitxers (o codi font) als fitxers d'aquest projecte:
    * src/main/java/es/gob/afirma/signers/pades/PdfPreProcessor.java
    * src/main/java/es/gob/afirma/signers/pades/PdfSessionManager.java
    
(4) Actualitzar els fitxers  PdfPreProcessor i PdfSessionManager amb els canvis marcats
    a PdfPreProcessor.java_COPIA i PdfSessionManager.java_COPIA

(5) Descarregar el binari de afirma-crypto-pdf-3.3.2-SNAPSHOT.jar

   (5.1) Copiar la llibreria a [portafib]\pluginsib-signatureweb\afirma-crypto-pdf i posar-li nom  afirma-crypto-pdf-3.3.2-SNAPSHOT_ORIGINAL.jar
   
   (5.2) Copiar la llibreria a [portafib]\pluginsib-signatureweb\afirma-crypto-pdf  sense canviar-li el nom (afirma-crypto-pdf-3.3.2-SNAPSHOT.jar)

(6) Recompilar aquest projecte: mvn clean install

(7) Editar el jar [portafib]\pluginsib-signatureweb\afirma-crypto-pdf\afirma-crypto-pdf-3.3.2-SNAPSHOT.jar emprant un editor de zips i substituir les classes generades ([portafib]\pluginsib-signatureweb\afirma-crypto-pdf\target\classes). Guardar
    
(8) Copiar el nou jar [portafib]\pluginsib-signatureweb\afirma-crypto-pdf\afirma-crypto-pdf-3.3.2-SNAPSHOT.jar a  https://github.com/GovernIB/maven/blob/gh-pages/maven/es/gob/afirma/afirma-crypto-pdf/3.3.2-SNAPSHOT/ (també actualitzar data del fitxer https://github.com/GovernIB/maven/blob/gh-pages/maven/es/gob/afirma/afirma-crypto-pdf/3.3.2-SNAPSHOT/afirma-crypto-pdf-3.3.2-SNAPSHOT.jar.lastUpdated)

(9) Esborrar el directori [HOME]\.m2\repository\es\gob\afirma

(10) Recompilar-ho tot