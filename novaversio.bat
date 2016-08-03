
@echo off 

IF "%~1" == "" GOTO SENSEPARAMS

@echo on
cmd /C mvn -Pws-portafib -Pws-portafib-callback-server -Pws-portafirmas -Pws-portafirmas-callback-server -Psqlgen -Pclientcert  -Pdesenvolupament -Pproduccio -DgroupId=es.caib.portafib -DartifactId=* versions:set -DnewVersion=%*

@echo. 
@echo. 
@echo --------------------------- IMPORTANT ---------------------------------
@echo ^|  Has d'actualitzar la propietat portafib.version del pom.xml arrel  ^|
@echo -----------------------------------------------------------------------
@echo.
@echo.
@echo off
GOTO EXIT

:SENSEPARAMS

@echo.
@echo.
echo Falta versio
@echo.
@echo.


:EXIT
