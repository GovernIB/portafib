
if defined CXF_HOME goto directExecution

set CXF_HOME=D:\dades\dades\CarpetesPersonals\Programacio\apache-cxf-2.7.4

set PATH=D:\dades\dades\CarpetesPersonals\Programacio\apache-cxf-2.7.4\bin;%PATH%

:directExecution

REM http://cxf.apache.org/docs/wsdl-to-java.html
REM http://stackoverflow.com/questions/12508741/jaxb-generating-jaxbelementstring-instead-of-string

wsdl2java.bat -verbose -impl -fe jaxws21 -b bindings.xml -d .\src\main\java indra-portafirmes.wsdl