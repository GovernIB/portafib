REM -b ./wsdl/binding.xml 
call wsconsume -k  https://des-afirma.redsara.es/afirmaws/services/DSSAfirmaSign?wsdl  -s src/main/java -n -p org.fundaciobit.plugins.signatureserver.afirmaserver.apiws
