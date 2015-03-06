
wget http://localhost:8080/portafib/portafirmascb/v0/PortafirmasCallBack?wsdl -O src/main/resources/wsdl/PortafirmasCallBack.wsdl
REM  -w  "classpath:wsdl/PortafirmasCallBack.wsdl" 
wsconsume -k -s src/main/java -o target -p es.indra.www.portafirmasmcgdws.mcgdws http://localhost:8080/portafib/portafirmascb/v0/PortafirmasCallBack?wsdl