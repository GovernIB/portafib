
wget http://localhost:8080/portafib/cb/v1/PortaFIBCallBack?wsdl  -O src/main/resources/wsdl/PortaFIBCallBack_v1.wsdl
REM -w  "classpath:wsdl/PortaFIBCallBack_v1.wsdl" 
call wsconsume -k -s src/main/java -n -p es.caib.portafib.ws.callback.api.v1 http://localhost:8080/portafib/cb/v1/PortaFIBCallBack?wsdl 
