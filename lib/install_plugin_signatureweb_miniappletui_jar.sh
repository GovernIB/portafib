#!/bin/bash

mvn install:install-file -Dfile=./plugin-signatureweb-miniappletui-signed-1.0.0.jar -DgroupId=es.caib.portafib -DartifactId=plugin-signatureweb-miniappletui-signed -Dversion=1.0.0  -Dpackaging=jar
