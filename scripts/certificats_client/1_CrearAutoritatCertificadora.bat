
REM http://www.adictosaltrabajo.com/tutoriales/tutoriales.php?pagina=JBossClientCert

REM 1.- Certificat Arrel

REM 1.1.- Crear Clau Privada (Password fundaciobit)
openssl genrsa -des3 -out fundaciobit_ca.key 4096    

REM  1.2- Crear certificat  de la CA:
REM  Country Name (2 letter code) [AU]:ES
REM  State or Province Name (full name) [Some-State]:Illes Balears
REM  Locality Name (eg, city) []:Palma
REM  Organization Name (eg, company) [Internet Widgits Pty Ltd]:Fundacio Bit
REM  Organizational Unit Name (eg, section) []:OTAE Unit
REM  Common Name (e.g. server FQDN or YOUR name) []:OTAE
REM  Email Address []:otae@fundaciobit.org
openssl req -x509 -days 9125 -new -key fundaciobit_ca.key -out fundaciobit_ca.crt

