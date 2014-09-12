

openssl req -config delegat.cfg -new -key fundaciobit_ca.key -out delegat.csr 

openssl x509 -req -days 365 -in delegat.csr -CA fundaciobit_ca.crt -CAkey fundaciobit_ca.key -set_serial 04 -out delegat.crt  

REM http://www.flatmtn.com/article/setting-openssl-create-certificates

REM 2.2.- Magatzem PKCS12 per Firefox
openssl pkcs12 -export -in delegat.crt -inkey fundaciobit_ca.key -out delegat.pcks12  