
REM Certificat de Client  (usuari anadal)

openssl req -config anadal.cfg -new -key fundaciobit_ca.key -out anadal.csr 

openssl x509 -req -days 365 -in anadal.csr -CA fundaciobit_ca.crt -CAkey fundaciobit_ca.key -set_serial 02 -out anadal.crt  

REM Magatzem PKCS12 per Firefox
echo PASSWORD = anadal
openssl pkcs12 -export -in anadal.crt -inkey fundaciobit_ca.key -out anadal.pcks12  






