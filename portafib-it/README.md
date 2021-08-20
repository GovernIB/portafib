# Execució de tests d'integració

## Requisits per l'execució dels tests

 - Tenir Docker instal·lat
 - JDK8 i Maven 3.6
 - Tenir un arxiu `keystore.p12` amb el certificat de proves amb que s'ha d'autenticar PortaFIB contra els serveis
 - Tenir un arxiu `keystore-sia.p12` amb el certicicat de proves amb que s'ha d'autenticar PortaFIB contra el servei específic de SIA
 - Crear un fitxer `secret.properties` a partir del fitxer `secret.properties.sample` amb els diferents passwords dels keystores, així com altres usuaris i passwords per accedir a serveis.

## Procediment per executar els tests

 - Compilar tests amb: `mvn -DskipTests clean package`
 - Posar entorn en marxa amb: `docker compose up --build`
 - A la finestra anterior sortiran els logs, per tant a una altre finestra:
   - Executar tests: `mvn verify`
   - Aturar entorn amb `docker compose down`