@echo off
type help.txt

cmd /C mvn -Pproduccio %* clean install 


if %errorlevel% EQU 0 (

echo off
IF DEFINED PORTAFIB_DEPLOY_DIR (
  echo --------- COPIANT EAR ---------
  echo on
  if exist "%PORTAFIB_DEPLOY_DIR%\portafib.ear" (
    del "%PORTAFIB_DEPLOY_DIR%\portafib.ear"
  )
  xcopy /Y ear\target\portafib.ear %PORTAFIB_DEPLOY_DIR%

  echo --------- COPIANT EAR PLUGINS ---------

  if exist "%PORTAFIB_DEPLOY_DIR%\portafib_plugins.ear" (
    del "%PORTAFIB_DEPLOY_DIR%\portafib_plugins.ear"
  )
  xcopy /Y .\earplugins\target\portafib_plugins.ear %PORTAFIB_DEPLOY_DIR%

) ELSE (
  echo  =================================================================
  echo    Definex la variable d'entorn PORTAFIB_DEPLOY_DIR apuntant al
  echo    directori de deploy del JBOSS  i automaticament s'hi copiara
  echo    l'ear generat.
  echo  =================================================================
)

)