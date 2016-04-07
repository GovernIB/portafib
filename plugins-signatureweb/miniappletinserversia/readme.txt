PASSA 0
=======

ERROR ==> Al compilar emprant maven no troba les llibreries SIA
SOLUCIO ==> S'ha de contactar amb SIA (www.sia.es) per obtenir els jar necessaris per la comunicació amb el servidor. Copiar les següents llibreries en els següents directoris:
           + gateway-api-2.4.05.jar => [portafib-x.y]\local-repo\com\openlandsw\rss\gateway-api\2.4.05
           + gateway-api-comun-2.4.05.jar => [portafib-x.y]\local-repo\com\openlandsw\rss\gateway-api-comun\2.4.05
           + hessian-3.2.0.jar => [portafib-x.y]\local-repo\com\caucho\hessian\3.2.0

Després s'haura d'executar la compilació del plugin de SIA amb el parametre -U:  "mvn -U clean install -DskipTests"
           

PASSA 1
=======

ERROR  ==> JBoss: exception unwrapping private key - java.security.InvalidKeyException: Illegal key size
SOLUCIO => Cargar librerías en JDK/JRE: http://www.oracle.com/technetwork/es/java/javase/downloads/jce-6-download-429243.html



PASSA 2
=======

ERROR ==>  JBoss: java.lang.SecurityException: JCE cannot authenticate the provider BC
SOLUCIO => (1) Copiar Llibreries de BC (bcpkix-jdk15on-1.53.jar i bcprov-jdk15on-1.53.jar) dins [JAVAHOME]\jre\lib\ext i
           (2) Afegir linia security.provider.XX=org.bouncycastle.jce.provider.BouncyCastleProvider  dins 
           el fitxer [JAVAHOME]\jre\lib\security\java.security on XX és el següent numero disponible.