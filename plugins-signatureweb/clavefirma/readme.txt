        

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