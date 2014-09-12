

for /f "tokens=* delims=" %%x in (versio.txt) do set PORTAFIB_VERSIO=%%x


zip -r portafib_%PORTAFIB_VERSIO%%1.zip scripts doc/pdf lib versio.txt portafib.genapp ear/target/portafib.ear plugins/plugins-documentcustody/filesystem/index.jsp ws/portafib_api/target/portafib-ws-api-1.0.0.jar ws\indra_api\target\portafib-ws-indra-api-1.0.0.jar ws/indra_callback_api/MCGDWS.wsdl ws/portafib_callback_api/PortaFIBCallBack_v1.wsdl -x "**/.svn**"  -x "scripts/sqlgenenerator/**" -x "scripts/pom.xml" 