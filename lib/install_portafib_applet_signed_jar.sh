#!/bin/bash

PORTAFIB_VERSIO=`cat ../versio.txt`
echo $PORTAFIB_VERSIO

mvn install:install-file -Dfile=./portafib-applet-signed-1.1.0.jar -DgroupId=es.caib.portafib -DartifactId=portafib-applet-signed -Dversion=$PORTAFIB_VERSIO  -Dpackaging=jar
