#!/bin/bash


env mvn -Pws-portafib -Pws-portafib-callback-server -Pws-portafirmas -Pws-portafirmas-callback-server -Psqlgen -Pclientcert  -Pdesenvolupament -Pproduccio -DgroupId=es.caib.portafib -DartifactId=* versions:set -DnewVersion=$@  

