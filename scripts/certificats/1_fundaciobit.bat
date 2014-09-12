
REM http://www.adictosaltrabajo.com/tutoriales/tutoriales.php?pagina=JBossClientCert

REM 1.- Certificat Arrel

REM 1.1.- Crear Clau Privada (Password fundaciobit)
openssl genrsa -des3 -out fundaciobit_ca.key 4096    

REM  1.2- Crear certificat  de la CA:
REM  Country Name (2 letter code) [AU]:ES
REM  State or Province Name (full name) [Some-State]:Illes Balears
REM  Locality Name (eg, city) []:Palma
REM  Organization Name (eg, company) [Internet Widgits Pty Ltd]:Fundacio Bit
REM  Organizational Unit Name (eg, section) []:OTAE
REM  Common Name (e.g. server FQDN or YOUR name) []:FundacioBit-OTAE
REM  Email Address []:otae@ibit.org
openssl req -x509 -days 365 -new -key fundaciobit_ca.key -out fundaciobit_ca.crt 

REM  1.3- Crear certificat per JBOSS a partir de la CA:
REM  Country Name (2 letter code) [AU]:ES
REM  State or Province Name (full name) [Some-State]:Illes Balears
REM  Locality Name (eg, city) []:Palma
REM  Organization Name (eg, company) [Internet Widgits Pty Ltd]:Fundacio Bit
REM  Organizational Unit Name (eg, section) []:OTAE
REM  Common Name (e.g. server FQDN or YOUR name) []:localhost
REM  Email Address []:otae@ibit.org
REM  Please enter the following 'extra' attributes to be sent with your certificate request
REM  A challenge password []:jboss
REM  An optional company name []:Fundacio Bit - JBoss
openssl req -new -key fundaciobit_ca.key -out jboss.csr 
REM (Password fundaciobit)
openssl x509 -req -days 365 -in jboss.csr -CA fundaciobit_ca.crt -CAkey fundaciobit_ca.key -set_serial 01 -out jboss.crt 


REM 2.- Creació de Claus i Certificats

REM Enter pass phrase for fundaciobit_ca.key: fundaciobit
REM Enter Export Password: fundaciobit
REM Verifying - Enter Export Password: fundaciobit
openssl pkcs12 -export -inkey fundaciobit_ca.key -in jboss.crt -out jboss.pkcs12 

REM Escribir contrase±a de almacÚn de claves de destino: fundaciobit
REM Volver a escribir la contrase±a nueva: fundaciobit
REM Escribir contrase±a de almacÚn de claves de origen: fundaciobit
REM Las entradas del alias 1 se han importado correctamente.
REM Comando de importaci¾n completado:  1 entradas importadas correctamente, 0 entradas incorrectas o canceladas
keytool -importkeystore -destkeystore jboss.keystore -srckeystore jboss.pkcs12 -srcstoretype PKCS12  

REM Escriba la contrase±a del almacÚn de claves: fundaciobit
REM Volver a escribir la contrase±a nueva: fundaciobit
REM  ...
REM +Confiar en este certificado? [no]:  si
REM Se ha a±adido el certificado al almacÚn de claves
keytool -importcert -alias "Fundacio BIT CA" -file fundaciobit_ca.crt -keystore jboss.truststore  





