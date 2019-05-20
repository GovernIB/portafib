@echo off
type help.txt

cmd /C mvn -DskipTests -Pdesenvolupament %* install

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED PORTAFIB_DEPLOY_DIR (
      for /f "tokens=* delims=" %%x in (versio.txt) do set PORTAFIB_VERSIO=%%x
	  @echo on
	  echo --------- COPIANT EAR %PORTAFIB_VERSIO% ---------

	  if exist "%PORTAFIB_DEPLOY_DIR%\portafib_plugins.ear" ( 
		del  "%PORTAFIB_DEPLOY_DIR%\portafib_plugins.ear"
	  )

	  xcopy /Y ear\target\portafib.ear %PORTAFIB_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn PORTAFIB_DEPLOY_DIR apuntant al
	  echo    directori de deploy del JBOSS  i automaticament s'hi copiara
	  echo    l'ear generat.
	  echo  =================================================================
	) 

)