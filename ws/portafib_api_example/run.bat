




ECHO OFF
CLS
:MENU
ECHO.
ECHO ...............................................
ECHO Escriu 0, 1 o 2 i pitja ENTER:
ECHO ...............................................
ECHO.
ECHO 0 - Sortir
ECHO 1 - Executa Exemple
ECHO 2 - Executa LoadUsers
ECHO.
SET /P M=Escriu 0, 1 o 2 i pitja ENTER:
IF %M%==0 GOTO EOF
IF %M%==1 GOTO EXAMPLE
IF %M%==2 GOTO LOADUSERS

GOTO EOF

:EXAMPLE

mvn exec:java -Dexec.mainClass="es.caib.portafib.ws.v1.example.Exemple" -Dexec.args="%1 %2 %3"

GOTO EOF


:LOADUSERS

mvn exec:java -Dexec.mainClass="es.caib.portafib.ws.v1.example.LoadUsers" -Dexec.args="%1 %2 %3"

GOTO EOF


EOF:

