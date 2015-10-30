
cmd /C mvn versions:set -DnewVersion=%* -Psqlgen -Papplet -Pclientcert -Pws-portafib -Pws-portafib-callback-server -Pws-portafirmas  -Pws-portafirmas-callback-server  -Pdesenvolupament -Pproduccio


@echo.
@echo.
@echo --------------------------- IMPORTANT ----------------------------
@echo ^|  El projectes del directori ws no s'actualitzen automaticament. ^|
@echo ^|  Per favor actualitzar la versio manualment.                    ^|
@echo -------------------------------------------------------------------
@echo.
@echo.