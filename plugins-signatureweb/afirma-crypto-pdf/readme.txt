


Aquest readme descriu les pases per generar una nova versi� de la llibreria afirma-crypto-pdf 
per poder suportar estampaci� de taula de firmes.

(1) Obtenir codi font de la versi� qeu s'utilitzi de afirma-crypto-pdf

(2) En el nostre cas nom�s requerim fer canvis als fitxers 
    * src/main/java/es/gob/afirma/signers/pades/PdfPreProcessor.java
    * src/main/java/es/gob/afirma/signers/pades/PdfSessionManager.java
    per aix� les hem ficat dins aquest projecte

(3) Actualitzar la llibreria seg�ent amb la nova versi� 
[portafib]\plugins-signatureweb\afirma-triphase-server-lib\local-repo\es\gob\afirma\afirma-crypto-pdf\3.3.2-SNAPSHOT
i marcar-la com a ORIGINAL

(4) Fer una altra c�pia dins d'aquest projecte amb el mateix nom del punt (3)

(5) Recompilar aquest projecte: mvn clean install

(6) Fer una c�pia del jar original amb el nom correcte:
    En el nostre cas afirma-crypto-pdf-3.3.2-SNAPSHOT.jar

(7) Substituir les classes generades dins target del jar 
    afirma-crypto-pdf-3.3.2-SNAPSHOT.jar i guardar
    
(8) Copiar el nou jar a  [portafib]\plugins-signatureweb\afirma-triphase-server-lib\local-repo\es\gob\afirma\afirma-crypto-pdf\[VERSIO]

(9) Avisar que s'ha d'esborrar el directori [HOME]\.m2\repository\es\gob\afirma