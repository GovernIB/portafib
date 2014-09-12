
wget http://localhost:8080/portafib/ws/v1/PortaFIBHelloWorld?wsdl  -O src/main/resources/wsdl/PortaFIBHelloWorld_v1.wsdl
call wsconsume -k -s src/main/java -n -p es.caib.portafib.ws.api.v1 http://localhost:8080/portafib/ws/v1/PortaFIBHelloWorld?wsdl

wget http://localhost:8080/portafib/ws/v1/PortaFIBUsuariAplicacio?wsdl  -O src/main/resources/wsdl/PortaFIBUsuariAplicacio_v1.wsdl
call wsconsume -k -s src/main/java -n -p es.caib.portafib.ws.api.v1 http://localhost:8080/portafib/ws/v1/PortaFIBUsuariAplicacio?wsdl

wget http://localhost:8080/portafib/ws/v1/PortaFIBUsuariEntitat?wsdl  -O src/main/resources/wsdl/PortaFIBUsuariEntitat_v1.wsdl
call wsconsume -k -s src/main/java -n -p es.caib.portafib.ws.api.v1 http://localhost:8080/portafib/ws/v1/PortaFIBUsuariEntitat?wsdl

wget http://localhost:8080/portafib/ws/v1/PortaFIBPeticioDeFirma?wsdl  -O src/main/resources/wsdl/PortaFIBPeticioDeFirma_v1.wsdl
call wsconsume -k -s src/main/java -n -p es.caib.portafib.ws.api.v1 http://localhost:8080/portafib/ws/v1/PortaFIBPeticioDeFirma?wsdl
