
REM Certificat de Client  

openssl req -config infousuari.cfg -new -key fundaciobit_ca.key -out certificatusuari.csr 

openssl x509 -req -days 365 -in certificatusuari.csr -CA fundaciobit_ca.crt -CAkey fundaciobit_ca.key -set_serial 02 -out certificatusuari.crt  

REM Magatzem PKCS12 per Firefox
echo PASSWORD = anadal
openssl pkcs12 -export -in certificatusuari.crt -inkey fundaciobit_ca.key -out certificatusuari.p12  

